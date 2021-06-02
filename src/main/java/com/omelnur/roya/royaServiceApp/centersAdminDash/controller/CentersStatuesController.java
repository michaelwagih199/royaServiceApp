package com.omelnur.roya.royaServiceApp.centersAdminDash.controller;

import com.omelnur.roya.royaServiceApp.centersAdminDash.service.CenterStauesService;
import com.omelnur.roya.royaServiceApp.patients.services.patientCycleStatues.PatientCycleStatuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("injectionEye")
    public ResponseEntity getCycleReportStatues(@RequestParam Long patientId, @RequestParam String eyeInjection) {
        return ResponseEntity.ok().body(patientCycleStatuesService.getCycleReportStatues(patientId, eyeInjection));
    }

    @PutMapping("testDone")
    public void updateCycleTestToDoneTest(@RequestParam Long cycleStatuesId) {
        patientCycleStatuesService.updateCycleTestToDoneTest(cycleStatuesId);
    }


}
