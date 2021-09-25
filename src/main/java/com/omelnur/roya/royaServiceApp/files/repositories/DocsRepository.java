package com.omelnur.roya.royaServiceApp.files.repositories;

import com.omelnur.roya.royaServiceApp.files.models.Docs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DocsRepository extends CrudRepository<Docs, Long> {

    @Query("SELECT d FROM Docs d WHERE d.docType.patientCycle.id =:cycleId and d.isArchived = false")
    Docs getCycleDocuments(Long cycleId);

    @Query("SELECT d FROM Docs d WHERE d.docType.patientCycle.patient.id =:patientId and d.isArchived = false")
    List<Docs> getPatientDocuments(Long patientId);

}
