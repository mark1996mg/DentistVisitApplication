package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.cgi.dentistapp.builders.DentistVisitEntityBuilder.aDentistVisitEntity;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DentistVisitRepositoryTest {

    @InjectMocks
    private DentistVisitRepository repository = new DentistVisitRepositoryImpl();

    @Mock
    private JpaDentistVisitRepository jpaRepository;

    @Test
    void savesDentistVisitEntity() {
        DentistVisitEntity dentistVisit = aDentistVisitEntity().build();

        repository.save(dentistVisit);

        verify(jpaRepository).save(dentistVisit);
    }

    @Test
    void deletesDentistVisitEntityById() {
        repository.deleteById(1L);

        verify(jpaRepository).deleteById(1L);
    }

    @Test
    void findsDentistVisitEntityById() {
        DentistVisitEntity expected = aDentistVisitEntity().build();

        when(jpaRepository.findOne(1L))
            .thenReturn(expected);

        DentistVisitEntity actual = repository.findOne(1L);
        assertThat(actual, is(expected));
    }

    @Test
    void findsAllDentistNames() {
        List<String> expected = asList("dentist-name-1", "dentist-name-2");

        when(jpaRepository.findAllDentistNames())
            .thenReturn(expected);

        List<String> actual = repository.findAllDentistNames();
        assertThat(actual, is(expected));
    }

    @Test
    void findsAllDentists() {
        List<DentistVisitEntity> expected = singletonList(aDentistVisitEntity().build());

        when(jpaRepository.findAll())
            .thenReturn(expected);

        List<DentistVisitEntity> actual = repository.findAll();
        assertThat(actual, is(expected));
    }


}