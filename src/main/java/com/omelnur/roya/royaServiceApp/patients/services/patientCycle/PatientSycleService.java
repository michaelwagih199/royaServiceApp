package com.omelnur.roya.royaServiceApp.patients.services.patientCycle;

import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintService;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycle;

import java.util.List;

public interface PatientSycleService extends BluePrintService<PatientCycle> {
    public abstract PatientCycle saveCycle(PatientCycle patientCycle,Long patientId,Long hospitalId);
    public abstract PatientCycle updateCycle(Long patientCycleId,PatientCycle patientCycle,Long patientId,Long hospitalId);
    public abstract List<PatientCycle> getCycleByPatientId(Long patientId);
}
