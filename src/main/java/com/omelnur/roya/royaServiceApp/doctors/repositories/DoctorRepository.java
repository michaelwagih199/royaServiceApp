package com.omelnur.roya.royaServiceApp.doctors.repositories;

import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    // named based bind parameters

    @Query("SELECT d FROM Doctor d WHERE d.doctorName = :doctorName")
    List<Doctor>findByDoctorNameBind(String doctorName);

    @Query("SELECT d.doctorName FROM Doctor d WHERE d.isArchived = false ")
    List<String> getDoctorNames();


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Doctor d set d.isArchived =:isArchived WHERE d.id =:id")
    void archive(@Param("isArchived") Boolean isArchived, @Param("id") Long id);

    List<Doctor> findByIsArchived(boolean b);

    @Query(value ="select count(distinct doctor.doctor_name) from doctor WHERE is_archived = false", nativeQuery = true)
    Integer countDistinctDoctor();

    @Query("SELECT d FROM Doctor d WHERE d.hospital.id = :hospitalID and d.isArchived = false")
    List<Doctor> findByHospitalId(Long hospitalID);

    @Query("SELECT d FROM Doctor d WHERE d.isArchived = false")
    Page<Doctor> getActivePagination(Pageable paging);


    @Query(value ="select count(id) from doctor d where (d.created_date BETWEEN :start AND :end) and is_archived = false", nativeQuery = true)
    Integer countBetweenDates(Date start, Date end);

}
