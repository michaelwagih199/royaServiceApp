package com.omelnur.roya.royaServiceApp.googleSheetsHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("api/googleSheet")
public class GoogleSheetController {

    @Autowired
    GoogleSheetsService googleSheetsService;

    @PostMapping("importDoctorNames")
    public void importDoctors() throws IOException, GeneralSecurityException {
        googleSheetsService.importDoctorsNames();
    }

    @PostMapping("importBasePatient")
    public void importBasePatient() throws IOException, GeneralSecurityException {
        googleSheetsService.importPatientData();
    }

    @PostMapping("importPatientCycle1")
    public void importPatientCycle1() throws IOException, GeneralSecurityException {
        googleSheetsService.importPatientCycle1();
    }

    @GetMapping("exportPatients")
    public ResponseEntity fromDb() {
       return ResponseEntity.ok().body(googleSheetsService.exportData());
    }
}
