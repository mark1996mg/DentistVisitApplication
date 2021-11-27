package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DentistVisitRepositoryImpl implements DentistVisitRepository {

    @Autowired
    private JpaDentistVisitRepository jpaRepository;

    @Override
    public void save(DentistVisitEntity dentistVisit) {
        jpaRepository.save(dentistVisit);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public DentistVisitEntity findOne(Long id) {
        return jpaRepository.findOne(id);
    }

    @Override
    public List<String> findAllDentistNames() {
        return jpaRepository.findAllDentistNames();
    }

    @Override
    public List<DentistVisitEntity> findAll() {
        return jpaRepository.findAll();
    }
}
