package com.joao.core.usecase;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.domain.SessionDomain;
import com.joao.core.exception.NotFoundException;
import com.joao.core.exception.SecondVoteAttemptException;
import com.joao.core.exception.SessionNotCreatedException;
import com.joao.core.gateway.AgendaGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.joao.core.enumeration.ExceptionCodeEnumeration.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class AgendaUseCase {

    private final AgendaGateway agendaGateway;

    public void newAgenda(final AgendaDomain agendaDomain) {
        log.info("creating new agenda");
        agendaGateway.save(agendaDomain.toBuilder()
                .createdAt(LocalDateTime.now())
                .build());
        log.info("agenda created successfully");
    }

    public void addSession(final AgendaDomain agendaDomain, final SessionDomain sessionDomain) {
        log.info("adding session to calendar");
        agendaGateway.save(agendaDomain.toBuilder()
                .sessionDomain(sessionDomain)
                .build());
        log.info("session added successfully");
    }

    public Page<AgendaDomain> getAllRegisteredGuidelines(final Integer page, final Integer linesPerPage, final String orderBy, final String direction) {
        log.info("generating pagination");
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        log.info("looking for list of agendas");
        return agendaGateway.findAll(pageRequest);
    }

    public AgendaDomain searchForAnAgenda(final Long id) {
        log.info("looking for an agenda by id");
        return agendaGateway.findById(id)
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(AGENDA_NOT_FOUND));
    }

    public void validateIfYouHaveSessionCreatedOnTheAgenda(final AgendaDomain agendaDomain) {
        if (Objects.isNull(agendaDomain.getSessionDomain())) {
            throw new SessionNotCreatedException(SESSION_NOT_CREATED);
        }

    }

    public void addVote(AgendaDomain agendaDomain) {
        log.info("add new vote agenda");
        agendaGateway.save(agendaDomain.toBuilder()
                .createdAt(LocalDateTime.now())
                .build());
        log.info("add vote successfully");
    }
}
