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

    @Query("SELECT p FROM Patient p WHERE p.patientName=:hospitalName and p.isArchived = false")
    List<Patient> findByPatientName(String hospitalName);

    @Query("SELECT p FROM Patient p WHERE p.phone=:hospitalName and p.isArchived = false")
    List<Patient> findByPhone(String hospitalName);

    @Query("SELECT p.phone FROM Patient p WHERE p.isArchived = false")
    List<String> getPhones();


    @Query("SELECT p.patientIDNumber FROM Patient p WHERE p.isArchived = false")
    List<String> getPatientIDNumbers();

    @Query("SELECT p FROM Patient p WHERE p.patientIDNumber=:patientCode and p.isArchived = false")
    List<Patient> findByPatientIDNumber(String patientCode);


    @Query("SELECT p.patientName,p.voiceMessageConsent, p.patientCode,p.patientIDNumber," +
            " p.gender,p.nationality,p.age,p.address,p.governorate,p.phone,p.phone2," +
             "p.doctor.doctorName, p.doctor.hospital.hospitalName, p.indication , p.diagnosedDate ,p.previousTreatment, p.startingLucentisDate FROM Patient p WHERE p.isArchived = false")
    List<Object>exportDB();


//    @Query("SELECT p FROM Patient p WHERE p.isArchived = false")
//    List<Patient> exportDB();


}
