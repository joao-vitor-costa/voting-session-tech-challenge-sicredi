package com.joao.core.usecase;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.domain.SessionDomain;
import com.joao.core.exception.CloseSessionException;
import com.joao.core.exception.OpenSessionException;
import com.joao.core.gateway.SessionGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.joao.core.enumeration.ExceptionCodeEnumeration.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith ( MockitoExtension.class )
class SessionUseCaseTest {

    @InjectMocks
    private SessionUseCase sessionUseCase;

    @Mock
    private AgendaUseCase agendaUseCase;

    @Mock
    private SessionGateway sessionGateway;

    @Test
    void should_not_open_a_session_when_a_schedule_already_has_a_session() {
        final var agendaId = UUID.randomUUID();
        final var sessionTime = 1L;
        final var agendaDomain = AgendaDomain.builder().id(agendaId).sessionDomain(SessionDomain.builder().id(UUID.randomUUID()).build()).build();

        when(agendaUseCase.searchForAnAgenda(agendaId)).thenReturn(agendaDomain);

        final var exception = assertThrows(OpenSessionException.class, () -> sessionUseCase.openAVotingSessionOnAnAgenda(agendaId, sessionTime));

        assertEquals(AGENDA_ALREADY_HAS_A_SESSION.message, exception.getMessage());

        verify(agendaUseCase).searchForAnAgenda(any());
    }

    @Test
    void must_open_a_session_when_a_schedule_does_not_have_a_session_created() {
        final var agendaId = UUID.randomUUID();
        final var sessionTime = 1L;
        final var agendaDomain = AgendaDomain.builder().id(agendaId).build();
        final var sessionCreated = SessionDomain.builder().build();
        final var sessionDomain = SessionDomain.builder()
                .createdAt(LocalDateTime.now())
                .closeDate(LocalDateTime.now().plusMinutes(1))
                .build();

        lenient().when(agendaUseCase.searchForAnAgenda(agendaId)).thenReturn(agendaDomain);
        lenient().when(sessionGateway.save(sessionDomain)).thenReturn(sessionCreated);

        sessionUseCase.openAVotingSessionOnAnAgenda(agendaId, sessionTime);

        verify(agendaUseCase).searchForAnAgenda(any());
        verify(sessionGateway).save(any());
        verify(agendaUseCase).addSession(any(), any());
    }

    @Test
    void validate_if_agenda_is_close_session() {
        final var sessionDomain = SessionDomain.builder()
                .createdAt(LocalDateTime.now())
                .closeDate(LocalDateTime.now().minusMinutes(10))
                .build();

        final var exception = assertThrows(CloseSessionException.class, () -> sessionUseCase.validateIfAgendaIsCloseSession(sessionDomain));

        assertEquals(SESSION_CLOSED.message, exception.getMessage());
    }

    @Test
    void validate_if_agenda_is_open_session() {
        final var sessionDomain = SessionDomain.builder()
                .createdAt(LocalDateTime.now())
                .closeDate(LocalDateTime.now().minusMinutes(10))
                .build();

        final var exception = assertThrows(OpenSessionException.class, () -> sessionUseCase.validateIfAgendaIsOpenSession(sessionDomain));

        assertEquals(SESSION_OPEN.message, exception.getMessage());
    }
}