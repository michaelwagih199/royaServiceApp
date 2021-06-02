package com.omelnur.roya.royaServiceApp.centersAdminDash.controller;

import com.omelnur.roya.royaServiceApp.hospitals.services.centersAdmin.CenterStauesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/centersAdmin")
public class CentersStatuesController {
    /**
     * todo: getActive Cycle by hospital():list<CanterPayload> - change statues of cycle (): void - get report of patient
     */
    @Autowired
    CenterStauesService centerStauesService;

    ResponseEntity getPatientWithActiveCycle(long hospitalId){
        return ResponseEntity.ok().body(centerStauesService.getPatientWithActiveCycle(hospitalId));
    }

}
