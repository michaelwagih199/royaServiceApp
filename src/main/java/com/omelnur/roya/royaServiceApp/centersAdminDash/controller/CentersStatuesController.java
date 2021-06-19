package com.omelnur.roya.royaServiceApp.centersAdminDash.controller;

import com.omelnur.roya.royaServiceApp.centersAdminDash.service.CenterStauesService;
import com.omelnur.roya.royaServiceApp.patients.services.patientCycleStatues.PatientCycleStatuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/centersAdmin")
public class CentersStatuesController {

    /**
     * todo: getActive Cycle by hospital():list<CanterPayload> - change statues of cycle (): void - get report of patient
     */
    @Autowired
    PatientCycleStatuesService patientCycleStatuesService;

    @GetMapping("/hospital")
    public ResponseEntity getActiveToHospital(@RequestParam Long hospitalId) {
        return ResponseEntity.ok().body(patientCycleStatuesService.getActiveToHospital(hospitalId));
    }

    @GetMapping("/hospital/patientId")
    public ResponseEntity getActiveToHospitalByPatientIdNumber(@RequestParam Long hospitalId,Long patientNationalId){
        return ResponseEntity.ok().body(patientCycleStatuesService.getActiveToHospital(hospitalId));
    }

    @GetMapping("/hospital/TestedDone")
    public ResponseEntity getTestedDoneToHospital(@RequestParam Long hospitalId,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return ResponseEntity.ok().body(patientCycleStatuesService.getHospitalTestedDone(hospitalId,start,end));
    }

    @GetMapping("injectionEye")
    public ResponseEntity getCycleReportStatues(@RequestParam Long patientId, @RequestParam String eyeInjection) {
        return ResponseEntity.ok().body(patientCycleStatuesService.getCycleReportStatues(patientId, eyeInjection));
    }

    @PutMapping("testDone")
    public void updateCycleTestToDoneTest(@RequestParam Long cycleStatuesId) {
        patientCycleStatuesService.updateCycleTestToDoneTest(cycleStatuesId);
    }


}
