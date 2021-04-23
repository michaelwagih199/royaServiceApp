package com.omelnur.roya.royaServiceApp.patients.repositories;

import com.omelnur.roya.royaServiceApp.patients.models.PatientCycle;
import org.springframework.data.repository.CrudRepository;

/**
 * @author omelnour created on ١٤‏/٤‏/٢٠٢١
 * inside the package - com.omelnur.roya.royaServiceApp.patients.repositories
 */
public interface PatientCycleRepository  extends CrudRepository<PatientCycle, Long> {
    
}
