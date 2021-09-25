package com.omelnur.roya.royaServiceApp.patients.services.patientCycleStatues;

import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycleStatues;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleRepository;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleStatuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    }

    @Override
    public Iterable<PatientCycleStatues> getAllObject() {
        return patientCycleStatuesRepository.findAll();
    }

    @Override
    public PatientCycleStatues findObject(Long id) {
        return null;
    }

    @Override
    public PatientCycleStatues findByPatientCycle(Long cycleId) {
        return patientCycleStatuesRepository.finByPatientCycleId(cycleId);
    }

    @Override
    public List<PatientCycleStatues> getActiveToHospital(Long hospitalId) {
        return patientCycleStatuesRepository.findAllActiveByHospitalId(hospitalId);
    }

    @Override
    public int getCycleReportStatues(Long patientId, String eyeInjection) {
        return patientCycleStatuesRepository.countLeftEyeTested(patientId, eyeInjection);
    }

    @Override
    public void updateCycleTestToDoneTest(Long cycleStatuesId) {
        patientCycleStatuesRepository.updateStatuesToDoneTest(cycleStatuesId);
    }

    @Override
    public List<PatientCycleStatues> getHospitalTestedDone(Long hospitalId, Date start, Date end) {
        return patientCycleStatuesRepository.findAllTestedDoneByHospitalId(hospitalId,start,end);
    }

    @Override
    public List<PatientCycleStatues> getActiveToHospitalByPatientName(Long hospitalId, String patientName) {
        return patientCycleStatuesRepository.findByPatientName(hospitalId,patientName);
    }

    @Override
    public List<PatientCycleStatues> findByVoucherNo(Long hospitalId, String voucherNo) {
        return patientCycleStatuesRepository.findByVoucherNo(hospitalId,voucherNo);
    }

}
