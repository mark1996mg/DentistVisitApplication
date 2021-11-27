package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistVisitEntity;

import java.util.List;

public interface DentistVisitRepository {

    void save(DentistVisitEntity dentistVisit);

    void deleteById(Long id);

    DentistVisitEntity findOne(Long id);

    List<String> findAllDentistNames();

    List<DentistVisitEntity> findAll();
}
