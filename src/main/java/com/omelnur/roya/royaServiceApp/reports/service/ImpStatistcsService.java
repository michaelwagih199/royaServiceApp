package com.omelnur.roya.royaServiceApp.reports.service;

import com.omelnur.roya.royaServiceApp.doctors.repositories.DoctorRepository;
import com.omelnur.roya.royaServiceApp.hospitals.repositories.HospitalRepository;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleRepository;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientRepository;
import com.omelnur.roya.royaServiceApp.patients.services.patients.PatientService;
import com.omelnur.roya.royaServiceApp.reports.models.StatistcsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ImpStatistcsService implements StatiscicsService {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientCycleRepository patientCycleRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public StatistcsModel getStatistcs(Date start, Date end) {
        return new StatistcsModel(
                patientRepository.countPatientsBetweenDates(start,end),
                patientRepository.countNewCases(start,end),
                patientCycleRepository.countBetweenDates(start,end),
                patientRepository.countMales(start,end),
                patientRepository.countFemales(start,end),
                patientRepository.countAgeClassA(start,end),
                patientRepository.countAgeClassB(start,end),
                patientRepository.countAgeClassC(start,end),
                doctorRepository.countBetweenDates(start,end),
                hospitalRepository.countBetweenDates(start,end));
    }

}
