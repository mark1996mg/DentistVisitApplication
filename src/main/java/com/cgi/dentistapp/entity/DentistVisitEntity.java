package com.cgi.dentistapp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dentist_visit", uniqueConstraints = {
    @UniqueConstraint(name = "UQ_d_visit_d_name_and_vis_time", columnNames = {"dentist_name", "visit_time"})})
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dentist_name")
    private String dentistName;

    @Column(name = "visit_time")
    private LocalDateTime visitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }
}
