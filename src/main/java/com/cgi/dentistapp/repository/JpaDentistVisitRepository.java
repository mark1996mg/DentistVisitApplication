package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaDentistVisitRepository extends JpaRepository<DentistVisitEntity, Long> {

    @Query("SELECT DISTINCT dentistName FROM DentistVisitEntity")
    List<String> findAllDentistNames();

    void deleteById(Long id);
}
