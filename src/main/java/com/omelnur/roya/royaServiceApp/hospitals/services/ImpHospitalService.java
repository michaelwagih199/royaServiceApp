package com.omelnur.roya.royaServiceApp.hospitals.services;


import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import com.omelnur.roya.royaServiceApp.hospitals.repositories.HospitalRepository;
import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OMELNOUR
 */
@Service
public class ImpHospitalService implements HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public Hospital createObject(Hospital object) {
        return hospitalRepository.save(object);
    }

    @Override
    public Hospital updateObject(Long id, Hospital object) {
        return hospitalRepository.findById(id).map(post -> {
            post.setHospitalName(object.getHospitalName());
            post.setHospitalPhone1(object.getHospitalPhone1());
            post.setHospitalPhone2(object.getHospitalPhone1());
            post.setId(object.getId());
            return hospitalRepository.save(post);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteObject(Long id) {
        hospitalRepository.archive(true, id);
    }

    @Deprecated
    @Override
    public Iterable<Hospital> getAllObject() {
        return null;
    }

    @Override
    public Hospital findObject(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Iterable<Hospital> getAllActive() {
        return hospitalRepository.findByIsArchived(false);
    }

    @Override
    public Page<Hospital> getActivePharmaciyPaginationNew(Integer page, Integer size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Hospital> pagedResult = hospitalRepository.getActivePagination(paging);
        return pagedResult;
    }

    @Override
    public List<String> getNames() {
        return hospitalRepository.getNames();
    }

    @Override
    public List<Hospital> findByHospitalName(String hospitalName) {
        return hospitalRepository.findByHospitalName(hospitalName);
    }

    @Override
    public List<String> getPhones() {
        return hospitalRepository.getPhones();
    }

    @Override
    public List<Hospital> findByHospitalPhone1(String hospitalPhone1) {
        return hospitalRepository.findByHospitalPhone1(hospitalPhone1);
    }
}
