package com.omelnur.roya.royaServiceApp.files.models;

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
@Table(name = "docs")
public class Docs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String docTitle;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private String fileBath;
    private long size;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "docTypeId")
    private DocType docType;

    @Type(type = "yes_no")
    private Boolean isArchived;

    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        isArchived = false;
    }

}
