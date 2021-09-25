package com.omelnur.roya.royaServiceApp.pv.models;


import com.omelnur.roya.royaServiceApp.hospitals.models.Hospital;
import com.omelnur.roya.royaServiceApp.patients.models.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pvCode;
    private String pvComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @Type(type="yes_no")
    private  Boolean isArchived;
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        isArchived = false;
    }

}
