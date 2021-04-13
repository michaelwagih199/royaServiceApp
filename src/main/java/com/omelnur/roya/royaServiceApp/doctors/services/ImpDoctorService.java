package com.omelnur.roya.royaServiceApp.doctors.services;

import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import com.omelnur.roya.royaServiceApp.doctors.repositories.DoctorRepository;
import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpDoctorService implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;


    @Override
    public Doctor createObject(Doctor object) {
        return doctorRepository.save(object);
    }

    @Override
    public Doctor updateObject(Long id, Doctor object) {
        return doctorRepository.findById(id).map(post -> {
            post.setDoctorName(object.getDoctorName());
            post.setId(object.getId());
            return doctorRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("id" + id + "notfound"));
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

}
