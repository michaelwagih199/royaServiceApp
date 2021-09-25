package com.omelnur.roya.royaServiceApp.pv.controllers;

import com.omelnur.roya.royaServiceApp.pv.models.PV;
import com.omelnur.roya.royaServiceApp.pv.services.PvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("api/pvs")
public class PvController {

    private final PvService pvService;

    @Autowired
    public PvController(PvService pvService) {
        this.pvService = pvService;
    }

    @PostMapping
    public PV createPV(@RequestBody PV pv,
                       @RequestParam Long patientId) {
        return pvService.createPV(pv,patientId);
    }

    @PutMapping
    public PV updatePV(@RequestBody PV pv,
                       @RequestParam Long patientId,
                       @RequestParam Long pvId){
        return pvService.updatePV(pv,patientId,pvId);
    }

    @GetMapping
    public ResponseEntity getPVs(@RequestParam Long patientId){
        return ResponseEntity.ok().body(pvService.getPVByPatientId(patientId));
    }


    @PutMapping("/archive")
    public void archive(@RequestParam(name = "id") @Positive Long id) {
        pvService.archive(id);
    }



}
