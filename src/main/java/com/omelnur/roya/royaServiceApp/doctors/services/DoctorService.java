package com.omelnur.roya.royaServiceApp.doctors.services;
import com.omelnur.roya.royaServiceApp.bluePrint.BluePrintService;
import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DoctorService extends BluePrintService<Doctor> {
    List<String> getDoctorNames();
    List<Doctor> searchByName(String doctorName);
    public abstract void archive(Long id);
    List<Doctor> findByIsArchived();
    List<Doctor> findByHospitalId(Long hospitalID);
    public abstract Page<Doctor> getPageable(Integer page, Integer size, String sortBy);
    Doctor addDoctor(Doctor object, Long centerId);
    Doctor updateDoctor(Long id, Doctor object, Long centerId);

}
