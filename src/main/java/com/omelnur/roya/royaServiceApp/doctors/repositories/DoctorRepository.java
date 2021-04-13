package com.omelnur.roya.royaServiceApp.doctors.repositories;

import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    // named based bind parameters

    @Query("SELECT d FROM Doctor d WHERE d.doctorName = :doctorName")
    List<Doctor>findByDoctorNameBind(String doctorName);

    @Query("SELECT d.doctorName FROM Doctor d")
    List<String> getDoctorNames();

//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE doctor p set is_archived = :isArchived  WHERE p.id = :id" , nativeQuery = true)
//    void archive(@Param("id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Doctor d set d.isArchived =:isArchived WHERE d.id =:id")
    void archive(@Param("isArchived") Boolean isArchived, @Param("id") Long id);

    List<Doctor> findByIsArchived(boolean b);

    @Query(value ="select count(distinct doctor.doctor_name) from doctor WHERE is_archived = false", nativeQuery = true)
    Integer countDistinctDoctor();

    @Query("SELECT d FROM Doctor d WHERE d.hospital.id = :hospitalID and d.isArchived = false")
    List<Doctor> findByHospitalId(Long hospitalID);
}
