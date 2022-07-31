package com.joao.core.usecase;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.gateway.AgendaGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
