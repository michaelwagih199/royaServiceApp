package com.omelnur.roya.royaServiceApp.hospitals.controllers;

import com.omelnur.roya.royaServiceApp.bluePrint.ControllerBluePrint;
import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import com.omelnur.roya.royaServiceApp.hospitals.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;

/**
 * @author OMELNOUR
 */

@RestController
@RequestMapping("api/hospitals")
public class HospitalController implements ControllerBluePrint<Hospital> {
    @Autowired
    HospitalService hospitalService;

    @Override
    public ResponseEntity getObject() {
        return null;
    }

    @GetMapping("{id}")
    @Override
    public Hospital getObjectById(@PathVariable Long id) {
        return hospitalService.findObject(id);
    }

    @GetMapping("pageable")
    public ResponseEntity<Map<String, Object>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Hospital> pharmacyPage;
        pharmacyPage = hospitalService.getActivePharmaciyPaginationNew(page, size, sortBy);
        Map<String, Object> response = new HashMap<>();
        response.put("hospitals", pharmacyPage.getContent());
        response.put("currentPage", pharmacyPage.getNumber());
        response.put("totalItems", pharmacyPage.getTotalElements());
        response.put("totalPages", pharmacyPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity getNames() {
        return ResponseEntity.ok().body(hospitalService.getNames());
    }

    @GetMapping("/phones")
    public ResponseEntity getPhones() {
        return ResponseEntity.ok().body(hospitalService.getPhones());
    }


    @PostMapping
    @Override
    public Hospital addObject(@RequestBody Hospital object) {
        return hospitalService.createObject(object);
    }

    @PutMapping("{id}")
    @Override
    public Hospital updateObject(@PathVariable Long id, @RequestBody Hospital object) {
        return hospitalService.updateObject(id, object);
    }

    @PutMapping("archive")
    @Override
    public void deleteObject(@RequestParam @Positive Long id) {
        hospitalService.deleteObject(id);
    }

    @GetMapping
    public ResponseEntity getAllActive() {
        return ResponseEntity.ok().body(hospitalService.getAllActive());
    }

    @GetMapping("name")
    public ResponseEntity findByHospitalName(@RequestParam String hospitalName){
        return ResponseEntity.ok().body(hospitalService.findByHospitalName(hospitalName));
    }

    @GetMapping("phone")
    public ResponseEntity findByHospitalPhone1(@RequestParam String hospitalPhone1){
        return ResponseEntity.ok().body(hospitalService.findByHospitalPhone1(hospitalPhone1));
    }

}
