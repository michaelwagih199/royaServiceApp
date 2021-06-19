package com.omelnur.roya.royaServiceApp.patients.services.patientCycleStatues;

import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintService;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycleStatues;

import java.util.Date;
import java.util.List;

public interface PatientCycleStatuesService extends BluePrintService<PatientCycleStatues> {
    public PatientCycleStatues findByPatientCycle(Long PatientCycleId);
    List<PatientCycleStatues> getActiveToHospital(Long hospitalId);
    int getCycleReportStatues(Long patientId, String eyeInjection);
    void updateCycleTestToDoneTest(Long cycleStatuesId);
    List<PatientCycleStatues> getHospitalTestedDone(Long hospitalId, Date start, Date end);
}
