package com.cgi.dentistapp.builders;

import com.cgi.dentistapp.entity.DentistVisitEntity;

import java.time.LocalDateTime;

public class DentistVisitEntityBuilder {

    private Long id = null;
    private String dentistName = "Dr. Dentist";
    private LocalDateTime visitTime = LocalDateTime.of(2021, 1, 1, 1, 1);

    public static DentistVisitEntityBuilder aDentistVisitEntity() {
        return new DentistVisitEntityBuilder();
    }

    public DentistVisitEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public DentistVisitEntityBuilder withDentistName(String dentistName) {
        this.dentistName = dentistName;
        return this;
    }

    public DentistVisitEntityBuilder withVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
        return this;
    }

    public DentistVisitEntity build() {
        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity();
        dentistVisitEntity.setId(id);
        dentistVisitEntity.setDentistName(dentistName);
        dentistVisitEntity.setVisitTime(visitTime);
        return dentistVisitEntity;
    }
}
