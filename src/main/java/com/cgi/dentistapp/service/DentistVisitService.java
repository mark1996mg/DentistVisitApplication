package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistVisitDTO;

import java.util.List;

public interface DentistVisitService {

    void addOrUpdateVisit(DentistVisitDTO dentistVisitDTO);

    void deleteVisit(Long id);

    DentistVisitDTO findDentistVisitById(Long id);

    List<String> findAllDentistNames();

    List<DentistVisitDTO> findAllVisits();
}
