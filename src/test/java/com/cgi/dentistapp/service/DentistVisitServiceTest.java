package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repository.DentistVisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.cgi.dentistapp.builders.DentistVisitDTOBuilder.aDentistVisitDTO;
import static com.cgi.dentistapp.builders.DentistVisitEntityBuilder.aDentistVisitEntity;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DentistVisitServiceTest {

    private static final long ID_1 = 1L;
    private static final long ID_2 = 1L;
    private static final String DENTIST_NAME = "dentist-name";
    private static final String ANOTHER_DENTIST_NAME = "another-dentist-name";
    private static final LocalDateTime TODAY = LocalDateTime.now();
    private static final LocalDateTime YESTERDAY = LocalDateTime.now().minusDays(1);

    @InjectMocks
    private DentistVisitService service = new DentistVisitServiceImpl();

    @Mock
    private DentistVisitRepository repository;

    @Captor
    private ArgumentCaptor<DentistVisitEntity> entityArgumentCaptor;

    @Test
    void addsDentistVisit() {
        DentistVisitDTO expected = aDentistVisitDTO()
            .withId(ID_1)
            .withDentistName(DENTIST_NAME)
            .withVisitTime(TODAY)
            .build();

        service.addOrUpdateVisit(expected);

        verify(repository).save(entityArgumentCaptor.capture());

        DentistVisitEntity actual = entityArgumentCaptor.getValue();
        assertThat(actual.getId(), is(ID_1));
        assertThat(actual.getDentistName(), is(DENTIST_NAME));
        assertThat(actual.getVisitTime(), is(TODAY));
    }

    @Test
    void deletesDentistVisit() {
        service.deleteVisit(ID_1);

        verify(repository).deleteById(ID_1);
    }

    @Test
    void findsDentistVisitById() {
        DentistVisitEntity expected = aDentistVisitEntity()
            .withId(ID_1)
            .withDentistName(DENTIST_NAME)
            .withVisitTime(TODAY)
            .build();

        when(repository.findOne(ID_1))
            .thenReturn(expected);

        DentistVisitDTO actual = service.findDentistVisitById(ID_1);
        assertThat(actual.getId(), is(ID_1));
        assertThat(actual.getDentistName(), is(DENTIST_NAME));
        assertThat(actual.getVisitTime(), is(TODAY));
    }

    @Test
    void findsAllDentistNames() {
        List<String> expected = singletonList(DENTIST_NAME);

        when(repository.findAllDentistNames())
            .thenReturn(expected);

        List<String> actual = service.findAllDentistNames();
        assertThat(actual, is(expected));
    }

    @Test
    void findsAllDentistVisits() {
        DentistVisitEntity dentistVisitEntity = aDentistVisitEntity()
            .withId(ID_1)
            .withDentistName(DENTIST_NAME)
            .withVisitTime(TODAY)
            .build();
        DentistVisitEntity anotherDentistVisitEntity = aDentistVisitEntity()
            .withId(ID_2)
            .withDentistName(ANOTHER_DENTIST_NAME)
            .withVisitTime(YESTERDAY)
            .build();

        when(repository.findAll())
            .thenReturn(Arrays.asList(dentistVisitEntity, anotherDentistVisitEntity));

        List<DentistVisitDTO> actual = service.findAllVisits();
        assertThat(actual, hasSize(2));

        assertThat(actual.get(0).getId(), is(ID_1));
        assertThat(actual.get(0).getDentistName(), is(DENTIST_NAME));
        assertThat(actual.get(0).getVisitTime(), is(TODAY));

        assertThat(actual.get(1).getId(), is(ID_2));
        assertThat(actual.get(1).getDentistName(), is(ANOTHER_DENTIST_NAME));
        assertThat(actual.get(1).getVisitTime(), is(YESTERDAY));
    }
}