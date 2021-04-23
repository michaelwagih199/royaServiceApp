package com.omelnur.roya.royaServiceApp.patients.repositories;

import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import com.omelnur.roya.royaServiceApp.patients.models.Patient;
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
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.isArchived = false")
    Page<Patient> getActivePagination(Pageable paging);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Patient p set p.isArchived =:isArchived WHERE p.id =:id")
    void archive(@Param("isArchived") Boolean isArchived, @Param("id") Long id);

    @Query("SELECT p.patientName FROM Patient p WHERE p.isArchived = false")
    List<String> getNames();

    List<Patient> findByPatientName(String hospitalName);
    List<Patient> findByPhone(String hospitalName);

    @Query("SELECT p.phone FROM Patient p WHERE p.isArchived = false")
    List<String> getPhones();


}
