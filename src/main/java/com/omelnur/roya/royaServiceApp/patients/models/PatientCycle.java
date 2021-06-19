package com.omelnur.roya.royaServiceApp.patients.models;

import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "patientCycle")
public class PatientCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date injectionDate;
    private Date octDate;
    private String voucherNo;
    private String injectionEye;
    private String comment;
    private String injectionPayment;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    private Patient  patient;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Type(type="yes_no")
    private  Boolean isArchived;

    @PrePersist
    protected void onCreate() {
        isArchived = false;
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

}
