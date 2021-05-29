package com.omelnur.roya.royaServiceApp.patients.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.util.Date;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author omelnour created on ١٤‏/٤‏/٢٠٢١
 * inside the package - com.omelnur.roya.royaServiceApp.patients.models
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PatientCycleStatues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private EnumCycleStatues cycleStatues;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_cycle_id")
    private PatientCycle patientCycle;

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
