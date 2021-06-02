package com.omelnur.roya.royaServiceApp.centersAdminDash.service;

import com.omelnur.roya.royaServiceApp.centersAdminDash.models.CenterStauesPayload;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleStatuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CenterStauesServiceImpl implements CenterStauesService {

    @Autowired
    PatientCycleStatuesRepository patientCycleStatuesRepository;

    @Override
    public List<CenterStauesPayload> getPatientWithActiveCycle(long hospitalId) {
        List<CenterStauesPayload> result = new ArrayList<>();
        patientCycleStatuesRepository.findAll().forEach(post->{
            CenterStauesPayload centerStauesPayload = new CenterStauesPayload();
            centerStauesPayload.setPatientCode(post.getPatientCycle().getPatient().getPatientCode());
            centerStauesPayload.setPatientIDNumber(post.getPatientCycle().getPatient().getPatientIDNumber());
            centerStauesPayload.setPatientName(post.getPatientCycle().getPatient().getPatientName());
            centerStauesPayload.setPhone(post.getPatientCycle().getPatient().getPhone());
        });
        return null;
    }

}
