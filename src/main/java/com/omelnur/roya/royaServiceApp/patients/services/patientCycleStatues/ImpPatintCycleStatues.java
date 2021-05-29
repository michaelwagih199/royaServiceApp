package com.omelnur.roya.royaServiceApp.patients.services.patientCycleStatues;

import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycleStatues;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleRepository;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleStatuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPatintCycleStatues implements PatientCycleStatuesService {
    @Autowired
    PatientCycleStatuesRepository patientCycleStatuesRepository;
    @Autowired
    PatientCycleRepository patientCycleRepository;

    @Override
    public PatientCycleStatues createObject(PatientCycleStatues object) {
        object.setPatientCycle(patientCycleRepository.findById(patientCycleRepository.count()).orElseThrow(ResourceNotFoundException::new));
        return patientCycleStatuesRepository.save(object);
    }

    @Override
    public PatientCycleStatues updateObject(Long id, PatientCycleStatues object) {
        return null;
    }

    @Override
    public void deleteObject(Long id) {
        patientCycleStatuesRepository.archive(true,id);
    }

    @Override
    public Iterable<PatientCycleStatues> getAllObject() {
        return null;
    }

    @Override
    public PatientCycleStatues findObject(Long id) {
        return null;
    }

    @Override
    public PatientCycleStatues findByPatientCycle(Long cycleId) {
        return patientCycleStatuesRepository.finByPatientCycleId(cycleId);
    }
}
