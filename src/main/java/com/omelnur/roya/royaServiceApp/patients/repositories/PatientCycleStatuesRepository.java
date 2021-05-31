package com.omelnur.roya.royaServiceApp.patients.repositories;

import com.omelnur.roya.royaServiceApp.patients.models.PatientCycleStatues;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author omelnour created on ١٤‏/٤‏/٢٠٢١
 * inside the package - com.omelnur.roya.royaServiceApp.patients.repositories
 */
public interface PatientCycleStatuesRepository  extends CrudRepository<PatientCycleStatues, Long> {

    @Query(value = "Select * from patient_cycle_statues as p where p.patient_cycle_id=:cycleId order by p.id desc limit 1",nativeQuery = true)
    PatientCycleStatues finByPatientCycleId(Long cycleId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE PatientCycleStatues p set p.isArchived =:isArchived WHERE p.id =:id")
    void archive(@Param("isArchived") Boolean isArchived, @Param("id") Long id);

}
