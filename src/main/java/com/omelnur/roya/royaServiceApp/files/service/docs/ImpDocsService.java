package com.omelnur.roya.royaServiceApp.files.service.docs;

import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import com.omelnur.roya.royaServiceApp.files.models.DocType;
import com.omelnur.roya.royaServiceApp.files.models.Docs;
import com.omelnur.roya.royaServiceApp.files.models.DocsTitle;
import com.omelnur.roya.royaServiceApp.files.repositories.DocsRepository;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpDocsService implements DocsService{

    @Autowired
    DocsRepository docsRepository;

    @Autowired
    PatientCycleRepository cyclesRepository;

    @Override
    public Docs createDocPatientCycle(Docs docs, Long cycleId) {
        DocType docType = new DocType();
        docType.setDocType(DocsTitle.cycleAttachment);
        docType.setPatientCycle(cyclesRepository.findById(cycleId).orElseThrow(ResourceNotFoundException::new));
        docs.setDocType(docType);
        return docsRepository.save(docs);
    }

    @Override
    public Docs getCycleDocuments(Long cycleId) {
        return docsRepository.getCycleDocuments(cycleId);
    }

    @Override
    public List<Docs> getPatientDocuments(Long patientId) {
        return docsRepository.getPatientDocuments(patientId);
    }


}
