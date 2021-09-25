package com.omelnur.roya.royaServiceApp.reports.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatistcsModel {

    private int totalNewPatients;
    private int NoNewCases;
    private int NOvoucherscode;
    private int NoMales;
    private int NoFemales;
    private int AgeFrom30to50;
    private int AgeFrom50to70;
    private int AgeFrom70to100;
    private int DoctorNo;
    private int centersNo ;

}
