package com.omelnur.roya.royaServiceApp.doctors.services;

import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import com.omelnur.roya.royaServiceApp.doctors.repositories.DoctorRepository;
import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import com.omelnur.roya.royaServiceApp.hospitals.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpDoctorService implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    HospitalRepository hospitalRepository;


    @Override
    public Doctor createObject(Doctor object) {
        return null;
    }

    @Override
    public Doctor updateObject(Long id, Doctor object) {
       return null;
    }

    @Override
    public void deleteObject(Long id) {
        doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id" + id + "notfound"));
        doctorRepository.deleteById(id);
    }

    @Override
    public Iterable<Doctor> getAllObject() {
        return doctorRepository.findAll();
    }


    @Override
    public Doctor findObject(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id" + id + "notfound"));
    }


    @Override
    public List<String> getDoctorNames() {
        return doctorRepository.getDoctorNames();
    }

    @Override
    public List<Doctor> searchByName(String doctorName) {
        return doctorRepository.findByDoctorNameBind(doctorName);
    }


    @Override
    public void archive(Long id) {
        doctorRepository.archive(true, id);
    }

    @Override
    public List<Doctor> findByIsArchived() {
        return doctorRepository.findByIsArchived(false);
    }

    @Override
    public List<Doctor> findByHospitalId(Long hospitalID) {
        return doctorRepository.findByHospitalId(hospitalID);
    }

    @Override
    public Page<Doctor> getPageable(Integer page, Integer size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Doctor> pagedResult = doctorRepository.getActivePagination(paging);
        return pagedResult;
    }

    @Override
    public Doctor addDoctor(Doctor object, Long centerId) {
        object.setHospital(hospitalRepository.findById(centerId).orElseThrow(ResourceNotFoundException::new));
        return doctorRepository.save(object);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor object, Long centerId) {
        return doctorRepository.findById(id).map(post -> {
            post.setDoctorName(object.getDoctorName());
            post.setId(object.getId());
            post.setHospital(hospitalRepository.findById(centerId).orElseThrow(ResourceNotFoundException::new));
            return doctorRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("id" + id + "notfound"));
    }

}
