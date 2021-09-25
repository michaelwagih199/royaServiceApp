package com.omelnur.roya.royaServiceApp.googleSheetsHelper;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import com.omelnur.roya.royaServiceApp.doctors.repositories.DoctorRepository;
import com.omelnur.roya.royaServiceApp.exceptions.ResourceNotFoundException;
import com.omelnur.roya.royaServiceApp.hospitals.repositories.HospitalRepository;
import com.omelnur.roya.royaServiceApp.patients.models.Patient;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycle;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycleStatues;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleRepository;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientCycleStatuesRepository;
import com.omelnur.roya.royaServiceApp.patients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoogleSheetsService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientCycleRepository patientCycleRepository;
    @Autowired
    PatientCycleStatuesRepository patientCycleStatuesRepository;

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static Sheets sheetsService;
    private static String APPLICATION_NAME = "Google sheets Example";
    private static String SPREAD_SHEET_ID_TO_DB = "1gMbNsbgVV-3xwS6vNQhijipl5_L6adPVW2lZwnLcwms";
    private static String SPREAD_SHEET_ID_FROM_DB = "1mzB0kazuNI4o-dZiO-UOTjfyHZn5lRXyygXJdviwJ74";

    private static Credential autherize() throws IOException, GeneralSecurityException {
        InputStream in = GoogleSheetsService.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(),
                new InputStreamReader(in));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
                scopes).setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline").build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = autherize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                credential).setApplicationName(APPLICATION_NAME).build();
    }


    public void importDoctorsNames() throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        String range = "RoyaData!A2:C125";
        ValueRange response = sheetsService.spreadsheets().values().get(SPREAD_SHEET_ID_TO_DB, range).execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("no data");
        } else {
            values.stream().forEach(p -> {
                Doctor doctor = new Doctor();
                doctor.setDoctorName(p.get(0).toString());
                doctor.setHospital(hospitalRepository
                        .findById(Long.valueOf(p.get(2).toString()))
                        .orElseThrow(ResourceNotFoundException::new));
                doctorRepository.save(doctor);
            });
        }
    }


    public void importPatientData() throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        String range = "RoyaData!d2:R125";
        ValueRange response = sheetsService.spreadsheets().values().get(SPREAD_SHEET_ID_TO_DB, range).execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("no data");
        } else {
            values.stream().forEach(p -> {
                Patient patient = new Patient();
                patient.setPatientName(p.get(0).toString());
                patient.setVoiceMessageConsent(p.get(1).toString());
                patient.setPatientIDNumber(p.get(2).toString());
                patient.setGender(p.get(3).toString());
                patient.setNationality(p.get(4).toString());
                patient.setAge(Integer.parseInt(p.get(5).toString()));
                patient.setAddress(p.get(6).toString());
                patient.setPhone("0" + p.get(7).toString());
                patient.setPhone2("0" + p.get(8).toString());
                patient.setDoctor(
                        doctorRepository.findById(Long.valueOf(p.get(10).toString())).orElseThrow(ResourceNotFoundException::new)
                );
                patient.setIndication(p.get(11).toString());
//                patient.setDiagnosedDate(LocalDate.parse(p.get(12).toString(), formatter));
                patient.setPreviousTreatment(p.get(13).toString());
//                patient.setStartingLucentisDate(LocalDate.parse(p.get(14).toString(), formatter));
                patient.setPatientCode("10" + patientRepository.count() + "02");
                patientRepository.save(patient);
            });
        }
    }


    public void importPatientCycle1() throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        String range = "RoyaData!D2:Z125";
        ValueRange response = sheetsService.spreadsheets().values().get(SPREAD_SHEET_ID_TO_DB, range).execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("no data");
        } else {
            values.stream().forEach(p -> {
                PatientCycle patientCycle = new PatientCycle();
                patientCycle.setPatient(patientRepository.findByPatientName(p.get(0).toString()).get(0));
                patientCycle.setHospital(hospitalRepository
                        .findById(Long.valueOf(p.get(22).toString()))
                        .orElseThrow(ResourceNotFoundException::new));
                patientCycle.setComment(p.get(21).toString());
//                patientCycle.setInjectionDate(LocalDate.parse(p.get(15).toString(), formatter));
                patientCycle.setInjectionPayment(p.get(16).toString());
                patientCycle.setInjectionEye(p.get(17).toString());
                patientCycle.setVoucherNo(p.get(19).toString());
//                patientCycle.setOctDate(LocalDate.parse(p.get(20).toString(), formatter));
                PatientCycleStatues patientCycleStatues = new PatientCycleStatues();
                patientCycleStatues.setPatientCycle(patientCycle);
                patientCycleRepository.save(patientCycle);
                patientCycleStatuesRepository.save(patientCycleStatues);
            });
        }
    }

    public List<Object> exportData() {
        return patientRepository.exportDB();
    }

    public List<Object> exportCycle() {
        return patientCycleRepository.exportDB();
    }
}
