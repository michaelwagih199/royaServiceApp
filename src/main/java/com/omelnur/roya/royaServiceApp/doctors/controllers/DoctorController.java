package com.omelnur.roya.royaServiceApp.doctors.controllers;

import com.omelnur.roya.royaServiceApp.bluePrint.ControllerBluePrint;
import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import com.omelnur.roya.royaServiceApp.doctors.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("api/doctors")
public class DoctorController implements ControllerBluePrint<Doctor> {

    @Autowired
    DoctorService doctorService;

    @GetMapping
    @Override
    public ResponseEntity getObject() {
        return new ResponseEntity<>(doctorService.findByIsArchived(), HttpStatus.OK);
    }

    @GetMapping("names")
    public ResponseEntity getDoctorNames() {
        return new ResponseEntity(doctorService.getDoctorNames(), HttpStatus.OK);
    }

    @GetMapping("name")
    public ResponseEntity searchByName(@RequestParam(name = "doctorName") String doctorName) {
        return new ResponseEntity(doctorService.searchByName(doctorName), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Override
    public Doctor getObjectById(@PathVariable @Positive Long id) {
        return doctorService.findObject(id);
    }

    @PostMapping
    @Override
    public Doctor addObject(@RequestBody Doctor object) {
        return doctorService.createObject(object);
    }

    @PutMapping("{id}")
    @Override
    public Doctor updateObject(@PathVariable @Positive Long id, @RequestBody Doctor object) {
        return doctorService.updateObject(id, object);
    }

    @DeleteMapping("{id}")
    @Override
    public void deleteObject(@PathVariable @Positive Long id) {
        doctorService.deleteObject(id);
    }

    /*
    @GetMapping("pagination")
    public ResponseEntity<Map<String, Object>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<Doctor> doctorPage = doctorService.getActivePatientNew(page, size, sortBy);
        doctorPage = doctorService.getActivePatientNew(page, size, sortBy);
        Map<String, Object> response = new HashMap<>();
        response.put("patients", doctorPage.getContent());
        response.put("currentPage", doctorPage.getNumber());
        response.put("totalItems", doctorPage.getTotalElements());
        response.put("totalPages", doctorPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
     */


    /**
     * archive not delete
     */
    @PutMapping("/archive")
    public void archive(@RequestParam(name = "id") @Positive Long id) {
        doctorService.archive(id);
    }

    @GetMapping("hospital")
    public ResponseEntity findByHospitalId(@RequestParam @Positive Long hospitalID) {
        return ResponseEntity.ok().body(doctorService.findByHospitalId(hospitalID));
    }

}
