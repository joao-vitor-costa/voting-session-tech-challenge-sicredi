package com.joao.core.usecase;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.domain.SessionDomain;
import com.joao.core.exception.CloseSessionException;
import com.joao.core.exception.OpenSessionException;
import com.joao.core.gateway.SessionGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.joao.core.enumeration.ExceptionCodeEnumeration.AGENDA_ALREADY_HAS_A_SESSION;
import static com.joao.core.enumeration.ExceptionCodeEnumeration.SESSION_CLOSED_FOR_VOTING;

@Component
@Slf4j
@RequiredArgsConstructor
public class SessionUseCase {

    private static final Long ONE_MINUTE = 1L;

    private final AgendaUseCase agendaUseCase;
    private final SessionGateway sessionGateway;

    public void openAVotingSessionOnAnAgenda(final Long agendaId, final Long sessionTime) {
        log.info("looking for agenda to open the session");
        final var agendaDomain = agendaUseCase.searchForAnAgenda(agendaId);
        log.info("validating if the agenda already has a session");
        validateIfAgendaHasSession(agendaDomain);
        log.info("valid agenda to have a voting session");
        final var closeDate = generateCloseDate(sessionTime);
        final var sessionDomain = SessionDomain.builder()
                .createdAt(LocalDateTime.now())
                .closeDate(closeDate)
                .build();
        log.info("creating session for voting on the agenda");
        final var sessionCreated = sessionGateway.save(sessionDomain);
        agendaUseCase.addSession(agendaDomain, sessionCreated);
        log.info("voting session created successfully");

    }

    public void validateIfAgendaIsOpenSession(final SessionDomain sessionDomain) {
        if (!sessionDomain.isCloseSession()) {
            throw new CloseSessionException(SESSION_CLOSED_FOR_VOTING);
        }
    }

    private LocalDateTime generateCloseDate(final Long sessionTime) {
        final var minutes = Objects.nonNull(sessionTime) ? sessionTime : ONE_MINUTE;
        return LocalDateTime.now().plusMinutes(minutes);
    }

    private void validateIfAgendaHasSession(final AgendaDomain agendaDomain) {
        if (Objects.nonNull(agendaDomain.getSessionDomain())) {
            throw new OpenSessionException(AGENDA_ALREADY_HAS_A_SESSION);
        }
    }

}
