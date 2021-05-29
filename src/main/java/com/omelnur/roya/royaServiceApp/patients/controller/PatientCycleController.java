package com.omelnur.roya.royaServiceApp.patients.controller;

import com.omelnur.roya.royaServiceApp.bluePrint.ControllerBluePrint;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycle;
import com.omelnur.roya.royaServiceApp.patients.services.patientCycle.PatientSycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/patientCycle")
public class PatientCycleController implements ControllerBluePrint<PatientCycle> {

    @Autowired
    PatientSycleService patientSycleService;

    @GetMapping
    @Override
    public ResponseEntity getObject() {
        return ResponseEntity.ok().body(patientSycleService.getAllObject());
    }


    @GetMapping("patient")
    public ResponseEntity getCycleByPatient(@RequestParam Long patientId ) {
        return ResponseEntity.ok().body(patientSycleService.getCycleByPatientId(patientId));
    }


    @Override
    public PatientCycle getObjectById(Long id) {
        return null;
    }


    @Override
    public PatientCycle addObject(@RequestBody PatientCycle object) {
        return null;
    }

    @PostMapping
    public PatientCycle saveCycle (@RequestBody PatientCycle patientCycle,@RequestParam Long patientId,@RequestParam Long hospitalId){
        return patientSycleService.saveCycle(patientCycle,patientId,hospitalId);
    }

    @Override
    public PatientCycle updateObject(Long id, PatientCycle object) {
        return null;
    }

    @PutMapping("{id}")
    public PatientCycle updatePatient(@PathVariable Long id,
                                      @RequestBody PatientCycle patientCycle,
                                      @RequestParam Long patientId,
                                      @RequestParam Long hospitalId){
        return patientSycleService.updateCycle(id,patientCycle,patientId,hospitalId);
    }

    @PutMapping("archive")
    @Override
    public void deleteObject(Long id) {
        patientSycleService.deleteObject(id);
    }

}
