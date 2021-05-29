package com.omelnur.roya.royaServiceApp.patients.controller;

import com.omelnur.roya.royaServiceApp.bluePrint.ControllerBluePrint;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycleStatues;
import com.omelnur.roya.royaServiceApp.patients.services.patientCycleStatues.PatientCycleStatuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cycleStatues")
public class PatientCycleStatuesController implements ControllerBluePrint<PatientCycleStatues> {

    @Autowired
    PatientCycleStatuesService patientCycleStatuesService;

    @Override
    public ResponseEntity getObject() {
        return null;
    }

    @GetMapping("{id}")
    @Override
    public PatientCycleStatues getObjectById(@PathVariable Long id) {
        return patientCycleStatuesService.findByPatientCycle(id);
    }

    @PostMapping
    @Override
    public PatientCycleStatues addObject(@RequestBody PatientCycleStatues object) {
        return patientCycleStatuesService.createObject(object);
    }

    @Override
    public PatientCycleStatues updateObject(Long id, PatientCycleStatues object) {
        return null;
    }

    @PutMapping("{id}")
    @Override
    public void deleteObject(@PathVariable Long id) {
        patientCycleStatuesService.deleteObject(id);
    }

}
