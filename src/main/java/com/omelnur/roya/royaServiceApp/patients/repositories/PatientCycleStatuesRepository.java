package com.omelnur.roya.royaServiceApp.patients.repositories;

import com.omelnur.roya.royaServiceApp.patients.models.PatientCycleStatues;
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
public interface PatientCycleStatuesRepository  extends CrudRepository<PatientCycleStatues, Long> {

    @Query(value = "Select * from patient_cycle_statues as p where p.patient_cycle_id=:cycleId order by p.id desc limit 1",nativeQuery = true)
    PatientCycleStatues finByPatientCycleId(Long cycleId);

    @Query("select p from PatientCycleStatues p where p.patientCycle.hospital.id=:hospitalId and p.cycleStatues=0 and p.isArchived = false")
    List<PatientCycleStatues> findAllActiveByHospitalId(Long hospitalId);

    @Query("select p from PatientCycleStatues p where p.patientCycle.hospital.id=:hospitalId and p.cycleStatues=0 and p.patientCycle.patient.patientName like %:patientName% and p.isArchived = false")
    List<PatientCycleStatues> findByPatientName(Long hospitalId,String patientName);

    @Query("select p from PatientCycleStatues p where p.patientCycle.hospital.id=:hospitalId and p.cycleStatues=0 and p.patientCycle.voucherNo like %:voucherNo% and p.isArchived = false")
    List<PatientCycleStatues> findByVoucherNo(Long hospitalId,String voucherNo );

    @Query("select p from PatientCycleStatues p where p.patientCycle.hospital.id=:hospitalId and p.cycleStatues=1 and (p.createdDate BETWEEN :start AND :end) and p.isArchived = false")
    List<PatientCycleStatues> findAllTestedDoneByHospitalId(Long hospitalId, Date start, Date end);

    @Query("select p from PatientCycleStatues p where p.patientCycle.hospital.id=:hospitalId and p.cycleStatues=0 and p.patientCycle.patient.patientIDNumber=:patientIdNumber and p.isArchived = false")
    List<PatientCycleStatues> findAllActiveByPatientIdNumber(Long hospitalId , String patientIdNumber);


    @Query("select count(p) from PatientCycleStatues p where p.patientCycle.patient.id=:patientId and p.cycleStatues=1 and p.patientCycle.injectionEye=:injectionEye and p.isArchived = false")
    int countLeftEyeTested(Long patientId , String injectionEye);

    // this method is archived by cycleId not id
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE PatientCycleStatues p set p.isArchived =:isArchived WHERE p.patientCycle.id =:id")
    void archive(@Param("isArchived") Boolean isArchived, @Param("id") Long id);


    // update statues to done Tests by statues cycle id
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE PatientCycleStatues p set p.cycleStatues =1 WHERE p.id =:id")
    void updateStatuesToDoneTest(@Param("id") Long id);

}
