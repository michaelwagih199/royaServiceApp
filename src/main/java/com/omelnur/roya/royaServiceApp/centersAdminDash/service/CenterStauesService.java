package com.omelnur.roya.royaServiceApp.centersAdminDash.service;

import com.omelnur.roya.royaServiceApp.centersAdminDash.models.CenterStauesPayload;

import java.util.List;

public interface CenterStauesService {
    List<CenterStauesPayload> getPatientWithActiveCycle(long hospitalId);

}
