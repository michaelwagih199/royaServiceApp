package com.omelnur.roya.royaServiceApp.hospitals.services;


import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintService;
import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author OMELNOUR
 */
public interface HospitalService extends BluePrintService<Hospital> {
    Iterable<Hospital> getAllActive();
    public abstract Page<Hospital> getActivePharmaciyPaginationNew(Integer page, Integer size, String sortBy);
    List<String> getNames();
    List<Hospital> findByHospitalName(String hospitalName);
    List<String>  getPhones();
    List<Hospital> findByHospitalPhone1(String hospitalPhone1);

}
