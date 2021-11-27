package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repository.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DentistVisitServiceImpl implements DentistVisitService {

    @Autowired
    private DentistVisitRepository repository;

    @Override
    public void addOrUpdateVisit(DentistVisitDTO dentistVisitDTO) {
        repository.save(mapDentistVisitDtoToEntity(dentistVisitDTO));
    }

    @Override
    public void deleteVisit(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DentistVisitDTO findDentistVisitById(Long id) {
        return mapDentistVisitEntityToDto(repository.findOne(id));
    }

    @Override
    public List<String> findAllDentistNames() {
        return repository.findAllDentistNames();
    }

    @Override
    public List<DentistVisitDTO> findAllVisits() {
        return repository.findAll().stream()
            .map(this::mapDentistVisitEntityToDto)
            .collect(Collectors.toList());
    }

    private DentistVisitEntity mapDentistVisitDtoToEntity(DentistVisitDTO dentistVisitDTO) {
        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity();
        dentistVisitEntity.setId(dentistVisitDTO.getId());
        dentistVisitEntity.setDentistName(dentistVisitDTO.getDentistName());
        dentistVisitEntity.setVisitTime(dentistVisitDTO.getVisitTime());
        return dentistVisitEntity;
    }

    private DentistVisitDTO mapDentistVisitEntityToDto(DentistVisitEntity dentistVisitEntity) {
        DentistVisitDTO dentistVisitDTO = new DentistVisitDTO();
        dentistVisitDTO.setId(dentistVisitEntity.getId());
        dentistVisitDTO.setDentistName(dentistVisitEntity.getDentistName());
        dentistVisitDTO.setVisitTime(dentistVisitEntity.getVisitTime());
        return dentistVisitDTO;
    }
}
