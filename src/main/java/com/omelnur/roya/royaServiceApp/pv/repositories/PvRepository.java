package com.omelnur.roya.royaServiceApp.pv.repositories;

import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import com.omelnur.roya.royaServiceApp.pv.models.PV;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PvRepository extends CrudRepository<PV,Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE PV p set p.isArchived =:isArchived WHERE p.id =:id")
    void archive(@Param("isArchived") Boolean isArchived, @Param("id") Long id);


    @Query("SELECT p FROM PV p WHERE p.patient.id = :patientId and p.isArchived = false")
    List<PV> findByPatientId(Long patientId);

}
