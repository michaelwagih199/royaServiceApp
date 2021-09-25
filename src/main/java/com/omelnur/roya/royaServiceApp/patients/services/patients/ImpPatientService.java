package com.omelnur.roya.royaServiceApp.patients.services.patients;

import com.omelnur.roya.royaServiceApp.doctors.repositories.DoctorRepository;
import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import com.omelnur.roya.royaServiceApp.patients.models.Patient;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author omelnour created on ١٤‏/٤‏/٢٠٢١
 * inside the package - com.omelnur.roya.royaServiceApp.patients.services.patients
 */

@Service
public class ImpPatientService implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Patient createObject(Patient object) {
        return patientRepository.save(object);
    }

    @Override
    public Patient updateObject(Long id, Patient object) {
        return null;
    }

    @Override
    public void deleteObject(Long id) {
        patientRepository.archive(true, id);
    }

    @Override
    public Iterable<Patient> getAllObject() {
        return null;
    }

    @Override
    public Patient findObject(Long id) {
        return patientRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }


    @Override
    public Page<Patient> getPageable(Integer page, Integer size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Patient> pagedResult = patientRepository.getActivePagination(paging);
        return pagedResult;
    }

    @Override
    public List<String> getNames() {
        return patientRepository.getNames();
    }

    @Override
    public List<String> getPhones() {
        return patientRepository.getPhones();
    }

    @Override
    public List<String> getPatientCode() {
        return patientRepository.getPatientCodes();
    }

    @Override
    public List<String> getIDNumbers() {
        return patientRepository.getPatientIDNumbers();
    }

    @Override
    public List<Patient> findByPatientName(String hospitalName) {
        return patientRepository.findByPatientName(hospitalName);
    }

    @Override
    public List<Patient> findByPatientCode(String patientCode) {
        return patientRepository.findByPatientCode(patientCode);
    }

    @Override
    public List<Patient> findByPatinetPhone1(String hospitalPhone1) {
        return patientRepository.findByPhone(hospitalPhone1);
    }

    @Override
    public Patient createPatient(Patient object, Long doctorId) {
        object.setPatientCode("20"+patientRepository.count());
        object.setDoctor(doctorRepository.findById(doctorId).orElseThrow(ResourceNotFoundException::new));
        return patientRepository.save(object);
    }

    @Override
    public Patient updatePatient(Patient patient, Long patientId, Long doctorId) {
        return patientRepository.findById(patientId).map(post -> {
            post.setPatientCode(patient.getPatientCode());
            post.setAddress(patient.getAddress());
            post.setAge(patient.getAge());
            post.setId(patient.getId());
            post.setPatientName(patient.getPatientName());
            post.setComments(patient.getComments());
            post.setPatientIDNumber(patient.getPatientIDNumber());
            post.setGender(patient.getGender());
            post.setDiagnosedDate(patient.getDiagnosedDate());
            post.setPreviousTreatment(patient.getPreviousTreatment());
            post.setGovernorate(patient.getGovernorate());
            post.setIndication(patient.getIndication());
            post.setNationality(patient.getNationality());
            post.setPhone(patient.getPhone());
            post.setPhone2(patient.getPhone2());
            post.setStartingLucentisDate(patient.getStartingLucentisDate());
            post.setIsArchived(patient.getIsArchived());
            post.setVoiceMessageConsent(patient.getVoiceMessageConsent());
            post.setDoctor(doctorRepository.findById(doctorId).orElseThrow(ResourceNotFoundException::new));
            return patientRepository.save(post);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Patient> findByPatientIDNumber(String patientCode) {
        return patientRepository.findByPatientIDNumber(patientCode);
    }


}
