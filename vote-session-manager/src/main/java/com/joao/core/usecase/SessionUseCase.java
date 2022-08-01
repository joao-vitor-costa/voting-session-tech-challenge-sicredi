package com.joao.core.usecase;

import com.joao.core.domain.SessionDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SessionUseCase {

    private AgendaUseCase agendaUseCase;

    public void openAVotingSessionOnAnAgenda(Long agendaId, Long sessionTime) {
        final var agendaDomain = agendaUseCase.searchForAnAgenda(agendaId);

        final var sessionDomain = SessionDomain.builder()
                .agendaDomain(null)
                .closeDate(null)
                .build();

    }

}
