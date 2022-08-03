package com.joao.core.usecase;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.domain.SessionDomain;
import com.joao.core.exception.NotFoundException;
import com.joao.core.exception.SessionNotCreatedException;
import com.joao.core.gateway.AgendaGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static com.joao.core.enumeration.ExceptionCodeEnumeration.AGENDA_NOT_FOUND;
import static com.joao.core.enumeration.ExceptionCodeEnumeration.SESSION_NOT_CREATED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith ( MockitoExtension.class )
class AgendaUseCaseTest {
    @InjectMocks
    private AgendaUseCase agendaUseCase;

    @Mock
    private AgendaGateway agendaGateway;


    @Test
    void must_create_new_agenda() {
        agendaUseCase.newAgenda(AgendaDomain.builder().build());

        verify(agendaGateway).save(any(AgendaDomain.class));
    }

    @Test
    void must_add_Session() {
        agendaUseCase.addSession(AgendaDomain.builder().build(), SessionDomain.builder().build());

        verify(agendaGateway).save(any(AgendaDomain.class));
    }

    @Test
    void must_get_all_registered_guidelines() {
        final var pageRequest = PageRequest.of(1, 2, Sort.Direction.valueOf("ASC"), "createdAt");
        final var agendaDomains = List.of(AgendaDomain.builder().id(1L).build());
        final var pagedAgenda = new PageImpl(agendaDomains, pageRequest, 1L);

        when(agendaGateway.findAll(pageRequest)).thenReturn(pagedAgenda);

        final var page = agendaUseCase.getAllRegisteredGuidelines(1, 2, "createdAt", "ASC");

        assertTrue(page.getContent().stream().anyMatch(o -> o.getId().equals(1L)));
        verify(agendaGateway).findAll(any());
    }

    @Test
    void must_search_for_an_agenda() {
        final var agendaId = 1L;
        final var agendaDomain = AgendaDomain.builder().id(agendaId).build();

        when(agendaGateway.findById(agendaId)).thenReturn(Optional.of(agendaDomain));

        final var result = agendaUseCase.searchForAnAgenda(agendaId);

        assertEquals(agendaId, result.getId());
    }

    @Test
    void should_give_an_error_when_looking_for_a_schedule_when_it_finds_the_schedule() {
        final var agendaId = 1L;

        when(agendaGateway.findById(agendaId)).thenReturn(Optional.empty());

        final var exception = assertThrows(NotFoundException.class, () -> agendaUseCase.searchForAnAgenda(agendaId));

        assertEquals(AGENDA_NOT_FOUND.message, exception.getMessage());
        verify(agendaGateway).findById(anyLong());
    }

    @Test
    void validate_if_you_hav_session_created_on_the_agenda() {

        final var exception = assertThrows(SessionNotCreatedException.class, () -> agendaUseCase.validateIfYouHaveSessionCreatedOnTheAgenda(AgendaDomain.builder().build()));

        assertEquals(SESSION_NOT_CREATED.message, exception.getMessage());
    }

}