package com.omelnur.roya.royaServiceApp.patients.repositories;

import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author omelnour created on ١٤‏/٤‏/٢٠٢١
 * inside the package - com.omelnur.roya.royaServiceApp.patients.repositories
 */
public interface PatientCycleRepository  extends CrudRepository<PatientCycle, Long> {

    @Query("SELECT p FROM PatientCycle p WHERE p.isArchived = false")
    Iterable<PatientCycle> findAllActive();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE PatientCycle p set p.isArchived =:isArchived WHERE p.id =:id")
    void archive(@Param("isArchived") Boolean isArchived, @Param("id") Long id);

    @Query("SELECT p FROM PatientCycle p WHERE p.patient.id=:id and p.isArchived = false")
    List<PatientCycle> findByPatientIdBind(Long id);
}
