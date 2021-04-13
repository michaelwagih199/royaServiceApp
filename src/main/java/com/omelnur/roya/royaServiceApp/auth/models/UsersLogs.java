package com.omelnur.roya.royaServiceApp.auth.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


/**
 * @author michael wagih
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "user_logs")
public class UsersLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;

    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_Id")
    private User user;

    @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userLogsType_Id")
    private UserLogsType userLogsType;


    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }


}
