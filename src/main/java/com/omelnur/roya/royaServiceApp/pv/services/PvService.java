package com.omelnur.roya.royaServiceApp.pv.services;

import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintService;
import com.omelnur.roya.royaServiceApp.pv.models.PV;

import java.util.List;

public interface PvService extends BluePrintService<PV> {
    public abstract PV createPV(PV pv, Long patientId);

    public abstract List<PV> getPVByPatientId(Long patientId);

    public abstract void archive(Long id);

    public PV updatePV(PV pv, Long patientId, Long pvId);

}
