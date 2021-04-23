package com.omelnur.roya.royaServiceApp.patients.controller;

import com.omelnur.roya.royaServiceApp.bluePrint.ControllerBluePrint;
import com.omelnur.roya.royaServiceApp.patients.models.Patient;
import com.omelnur.roya.royaServiceApp.patients.services.patients.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;

/**
 * @author omelnour created on ١٤‏/٤‏/٢٠٢١
 * inside the package - com.omelnur.roya.royaServiceApp.patients.controller
 */
@RestController
@RequestMapping("api/patients")
public class PatientController implements ControllerBluePrint<Patient> {

    /**
     * todo (
     * add patient - update patient - archive patient
     * - get all active - get by name - get by phone -
     */

    @Autowired
    PatientService patientService;

    @GetMapping
    @Override
    public ResponseEntity getObject() {
        return ResponseEntity.ok().body(patientService.getAllObject());
    }

    @GetMapping("{id}")
    @Override
    public Patient getObjectById(@PathVariable Long id) {
        return patientService.findObject(id);
    }

    @GetMapping("pageable")
    public ResponseEntity<Map<String, Object>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<Patient> pageList;
        pageList = patientService.getPageable(page, size, sortBy);
        Map<String, Object> response = new HashMap<>();
        response.put("patients", pageList.getContent());
        response.put("currentPage", pageList.getNumber());
        response.put("totalItems", pageList.getTotalElements());
        response.put("totalPages", pageList.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/names")
    public ResponseEntity getNames() {
        return ResponseEntity.ok().body(patientService.getNames());
    }

    @GetMapping("/phones")
    public ResponseEntity getPhones() {
        return ResponseEntity.ok().body(patientService.getPhones());
    }

    @GetMapping("name")
    public ResponseEntity findByHospitalName(@RequestParam String patientName) {
        return ResponseEntity.ok().body(patientService.findByPatientName(patientName));
    }

    @GetMapping("phone")
    public ResponseEntity findByHospitalPhone1(@RequestParam String phone) {
        return ResponseEntity.ok().body(patientService.findByPatinetPhone1(phone));
    }

    @Override
    public Patient addObject(@RequestBody Patient object) {
        return null;
    }

    @PostMapping
    public Patient addPatient(@RequestBody Patient object,
                              @RequestParam Long doctorId) {
        return patientService.createPatient(object, doctorId);
    }

    @PutMapping
    @PostMapping
    public Patient updatePatient(@RequestBody Patient patient,
                                 @RequestParam Long patientId,
                                 @RequestParam Long doctorId) {
        return patientService.updatePatient(patient,patientId, doctorId);
    }


    @Override
    public Patient updateObject(Long id, Patient object) {
        return null;
    }

    @PutMapping("/archive")
    @Override
    public void deleteObject(@RequestParam @Positive Long id) {
        patientService.deleteObject(id);
    }
}
