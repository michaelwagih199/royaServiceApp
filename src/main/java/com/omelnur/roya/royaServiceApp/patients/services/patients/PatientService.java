package com.omelnur.roya.royaServiceApp.patients.services.patients;

import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintService;
import com.omelnur.roya.royaServiceApp.patients.models.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author omelnour created on ١٤‏/٤‏/٢٠٢١
 * inside the package - com.omelnur.roya.royaServiceApp.patients.services.patients
 */

public interface PatientService extends BluePrintService<Patient> {
    Page<Patient> getPageable(Integer page, Integer size, String sortBy);
    List<String> getNames();
    List<String>getPhones();
    List<String>getPatientCode();
    List<String> getIDNumbers();
    List<Patient> findByPatientName(String hospitalName);
    List<Patient> findByPatientCode(String patientCode);
    List<Patient> findByPatinetPhone1(String hospitalPhone1);
    Patient createPatient(Patient object, Long doctorId);
    Patient updatePatient(Patient patient, Long patientId, Long doctorId);
    List<Patient> findByPatientIDNumber(String patientCode);


}
