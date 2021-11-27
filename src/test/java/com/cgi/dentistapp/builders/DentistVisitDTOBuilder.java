package com.cgi.dentistapp.builders;

import com.cgi.dentistapp.dto.DentistVisitDTO;

import java.time.LocalDateTime;

public class DentistVisitDTOBuilder {

    private Long id = null;
    private String dentistName = "Dr. Dentist";
    private LocalDateTime visitTime = LocalDateTime.of(2021, 1, 1, 1, 1);

    public static DentistVisitDTOBuilder aDentistVisitDTO() {
        return new DentistVisitDTOBuilder();
    }

    public DentistVisitDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public DentistVisitDTOBuilder withDentistName(String dentistName) {
        this.dentistName = dentistName;
        return this;
    }

    public DentistVisitDTOBuilder withVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
        return this;
    }

    public DentistVisitDTO build() {
        DentistVisitDTO dentistVisitDTO = new DentistVisitDTO();
        dentistVisitDTO.setId(id);
        dentistVisitDTO.setDentistName(dentistName);
        dentistVisitDTO.setVisitTime(visitTime);
        return dentistVisitDTO;
    }
}
