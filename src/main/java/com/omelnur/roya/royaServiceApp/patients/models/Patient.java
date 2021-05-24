package com.omelnur.roya.royaServiceApp.patients.models;

import com.omelnur.roya.royaServiceApp.doctors.models.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author michael wagih
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "patients")
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientCode;
    private String age;
    private String gender;
    @NotNull(message = "phone is required")
    private String phone;
    private String phone2;
    @NotNull(message = "patientName is required")
    private String patientName;
    private String patientIDNumber;
    private String comments;
    private String address;
    private String governorate;
    private String nationality;
    private String indication;
    private String previousTreatment;
    private LocalDate diagnosedDate;
    private LocalDate startingLucentisDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

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
