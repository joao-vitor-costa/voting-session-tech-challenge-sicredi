package com.joao.core.usecase;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.gateway.AgendaGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class AgendaUseCase {

    private final AgendaGateway agendaGateway;

    public void newAgenda(final AgendaDomain agendaDomain) {
        log.info("creating new agenda");
        agendaGateway.create(agendaDomain.toBuilder()
                .createdAt(LocalDateTime.now())
                .build());
        log.info("agenda created successfully");
    }

    public Page<AgendaDomain> getAllRegisteredGuidelines(final Integer page, final Integer linesPerPage, final String orderBy, final String direction) {
        log.info("generating pagination");
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        log.info("looking for list of agendas");
        return agendaGateway.findAll(pageRequest);
    }

    public AgendaDomain searchForAnAgenda(final Long id) {
        log.info("looking for an agenda by id");
        return agendaGateway.findById(id);
    }
}
