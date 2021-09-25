package com.omelnur.roya.royaServiceApp.files.service.docs;


import com.omelnur.roya.royaServiceApp.files.models.Docs;

import java.util.List;


public interface DocsService {
    public abstract Docs createDocPatientCycle(Docs docs , Long cycleId);
    public abstract Docs getCycleDocuments(Long cycleId);
    public abstract List<Docs> getPatientDocuments(Long patientId);
}
