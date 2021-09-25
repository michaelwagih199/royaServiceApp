package com.omelnur.roya.royaServiceApp.patients.repositories;

import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import com.omelnur.roya.royaServiceApp.patients.models.Patient;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycleStatues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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



    @Query("SELECT p FROM Patient p WHERE p.patientName like %:hospitalName% and p.isArchived = false")
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
             "p.doctor.doctorName, p.doctor.hospital.hospitalName, p.indication , DATE_FORMAT(p.diagnosedDate,'%d-%m-%Y')  ,p.previousTreatment, DATE_FORMAT( p.startingLucentisDate,'%d-%m-%Y') FROM Patient p WHERE p.isArchived = false")
    List<Object>exportDB();

    @Query("SELECT p.patientCode FROM Patient p WHERE p.isArchived = false")
    List<String> getPatientCodes();

    @Query("SELECT p FROM Patient p WHERE p.patientCode =:patientCode and p.isArchived = false")
    List<Patient> findByPatientCode(String patientCode);


    // reports


    @Query(value ="select count(id) from patients p where (p.created_date BETWEEN :start AND :end) and is_archived = false", nativeQuery = true)
    Integer countPatientsBetweenDates(Date start, Date end);

    @Query(value ="select count(id) from patients p where (p.created_date BETWEEN :start AND :end) and p.gender ='male' and is_archived = false", nativeQuery = true)
    Integer countMales(Date start, Date end);

    @Query(value ="select count(id) from patients p where (p.created_date BETWEEN :start AND :end) and p.gender ='female' and is_archived = false", nativeQuery = true)
    Integer countFemales(Date start, Date end);

    @Query(value ="select count(id) from patients p where (p.created_date BETWEEN :start AND :end) and age>0 and age<50 and is_archived = false", nativeQuery = true)
    Integer countAgeClassA(Date start, Date end);

    @Query(value ="select count(id) from patients p where (p.created_date BETWEEN :start AND :end) and age>50 and age<70 and is_archived = false", nativeQuery = true)
    Integer countAgeClassB(Date start, Date end);

    @Query(value ="select count(id) from patients p where (p.created_date BETWEEN :start AND :end) and age>70 and age<100 and is_archived = false", nativeQuery = true)
    Integer countAgeClassC(Date start, Date end);

    @Query(value ="select count(p.id) from patients p join patient_cycle c on p.id = c.patient_id where (p.created_date BETWEEN :start AND :end) and  p.starting_lucentis_date = c.injection_date and p.is_archived = false", nativeQuery = true)
    Integer countNewCases(Date start, Date end);



}
