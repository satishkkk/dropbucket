package com.sjsu.dropbucket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class File {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long fileId;

    @Column(nullable = false, length = 512)
    private String fileName;

    @Column(length = 512)
    private String fileDescription;

    @Column(name = "file_URL",nullable = false, length = 512)
    private String fileURL;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTime;

    @Column(nullable = false)
    private Boolean isDeleted;

}
