package com.omelnur.roya.royaServiceApp.centersAdminDash.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CenterStauesPayload {
    private String patientCode;
    private String phone;
    private String patientName;
    private String patientIDNumber;
    private LocalDate injectionDate;
    private LocalDate octDate;
    private String voucherNo;
    private String injectionEye;
    private String injectionPayment;

}
