package com.omelnur.roya.royaServiceApp.pv.services;

import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientRepository;
import com.omelnur.roya.royaServiceApp.pv.models.PV;
import com.omelnur.roya.royaServiceApp.pv.repositories.PvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpPvService implements PvService {

    private final PvRepository pvRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public ImpPvService(PvRepository pvRepository, PatientRepository patientRepository) {
        this.pvRepository = pvRepository;
        this.patientRepository = patientRepository;
    }

    @Deprecated
    @Override
    public PV createObject(PV object) {
        return null;
    }

    @Override
    public PV updateObject(Long id, PV object) {
        return null;
    }

    @Deprecated
    @Override
    public void deleteObject(Long id) {

    }

    @Override
    public Iterable<PV> getAllObject() {
        return null;
    }

    @Override
    public PV findObject(Long id) {
        return null;
    }

    @Override
    public PV createPV(PV pv, Long patientId) {
        pv.setPatient(patientRepository.findById(patientId).orElseThrow(ResourceNotFoundException::new));
        return pvRepository.save(pv);
    }

    @Override
    public List<PV> getPVByPatientId(Long patientId) {
        return pvRepository.findByPatientId(patientId);
    }

    @Override
    public void archive(Long id) {
        pvRepository.archive(true, id);
    }

    @Override
    public PV updatePV(PV pv, Long patientId, Long pvId) {
        return pvRepository.findById(pvId).map(post -> {
            post.setPatient(
                    patientRepository.findById(patientId).orElseThrow(ResourceNotFoundException::new)
            );
            post.setId(pvId);
            post.setIsArchived(false);
            post.setPvComment(pv.getPvComment());
            post.setPvCode(pv.getPvCode());
            return pvRepository.save(post);
        }).orElseThrow(ResourceNotFoundException::new);
    }

}
