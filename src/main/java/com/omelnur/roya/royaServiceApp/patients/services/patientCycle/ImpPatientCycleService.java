package com.omelnur.roya.royaServiceApp.patients.services.patientCycle;

import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import com.omelnur.roya.royaServiceApp.hospitals.repositories.HospitalRepository;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycle;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleRepository;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleStatuesRepository;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class ImpPatientCycleService implements PatientSycleService{

    @Autowired
    PatientCycleRepository patientCycleRepository;

    @Autowired
    PatientCycleStatuesRepository patientCycleStatuesRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public PatientCycle createObject(PatientCycle object) {
        return patientCycleRepository.save(object);
    }

    @Override
    public PatientCycle updateObject(Long id, PatientCycle object) {
        return null;
    }

    @Override
    public void deleteObject(Long id) {
        patientCycleRepository.archive(true,id);
        // archive repository statues
        patientCycleStatuesRepository.archive(true,id);
    }

    @Override
    public Iterable<PatientCycle> getAllObject() {
        return patientCycleRepository.findAllActive();
    }

    @Override
    public PatientCycle findObject(Long id) {
        return null;
    }

    @Override
    public PatientCycle saveCycle(PatientCycle patientCycle, Long patientId, Long hospitalId) {
        patientCycle.setHospital(hospitalRepository.findById(hospitalId).orElseThrow(ResourceNotFoundException::new));
        patientCycle.setPatient(patientRepository.findById(patientId).orElseThrow(ResourceNotFoundException::new));
        return patientCycleRepository.save(patientCycle);
    }

    @Override
    public PatientCycle updateCycle(Long patientCycleId,PatientCycle object, Long patientId, Long hospitalId) {
        PatientCycle patientCycle555 = new PatientCycle();
        patientCycle555.setPatient(patientRepository.findById(patientId).orElseThrow(ResolutionException::new));
        patientCycle555.setHospital(hospitalRepository.findById(hospitalId).orElseThrow(ResolutionException::new));
        patientCycle555.setInjectionDate(object.getInjectionDate());
        patientCycle555.setInjectionEye(object.getInjectionEye());
        patientCycle555.setOctDate(object.getOctDate());
        patientCycle555.setCreated(object.getCreated());
        patientCycle555.setVoucherNo(object.getVoucherNo());
        patientCycle555.setInjectionPayment(object.getInjectionPayment());
        patientCycle555.setIsArchived(object.getIsArchived());
        patientCycle555.setComment(object.getComment());
        patientCycle555.setId(patientCycleId);

        return patientCycleRepository.save(patientCycle555);
    }

    @Override
    public List<PatientCycle> getCycleByPatientId(Long id) {
        return patientCycleRepository.findByPatientIdBind(id);
    }

    @Override
    public List<String> getVouchers() {
        return patientCycleRepository.getVouchers();
    }
}
