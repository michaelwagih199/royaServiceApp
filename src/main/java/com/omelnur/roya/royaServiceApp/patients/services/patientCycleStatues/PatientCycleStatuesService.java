package com.omelnur.roya.royaServiceApp.patients.services.patientCycleStatues;

import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintService;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycleStatues;

public interface PatientCycleStatuesService extends BluePrintService<PatientCycleStatues> {
    public PatientCycleStatues findByPatientCycle(Long PatientCycleId);
}
