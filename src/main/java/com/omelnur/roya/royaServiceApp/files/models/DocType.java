package com.omelnur.roya.royaServiceApp.files.models;

import com.omelnur.roya.royaServiceApp.patients.models.Patient;
import com.omelnur.roya.royaServiceApp.patients.models.PatientCycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "docType")
public class DocType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DocsTitle docType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientCycleId")
    private PatientCycle patientCycle;


}
