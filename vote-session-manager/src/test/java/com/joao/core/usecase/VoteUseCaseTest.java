package com.joao.core.usecase;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.domain.SessionDomain;
import com.joao.core.domain.VoteDomain;
import com.joao.core.domain.VoteResultDomain;
import com.joao.core.enumeration.AssociateStatus;
import com.joao.core.enumeration.VoteDecisionEnumeration;
import com.joao.core.gateway.VoteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith ( MockitoExtension.class )
class VoteUseCaseTest {
    @InjectMocks
    private VoteUseCase voteUseCase;

    @Mock
    private AgendaUseCase agendaUseCase;

    @Mock
    private SessionUseCase sessionUseCase;

    @Mock
    private AssociateUseCase associateUseCase;

    @Mock
    private VoteGateway voteGateway;

    @Test
    void must_cast_a_vote_on_an_agenda_when_successful() {
        final var associateId = "46521623-8d87-4774-b84e-e24ddd898828";
        final Long agendaId = 1L;
        final var cpf = "189856191734";
        final var voteDecisionEnumeration = VoteDecisionEnumeration.SIM;
        final var associateStatus = AssociateStatus.ABLE_TO_VOTE;
        final var agendaDomain = AgendaDomain.builder()
                .id(agendaId)
                .createdAt(LocalDateTime.now().minusMinutes(10))
                .title("Lorem Ipsum")
                .description("Lorem Ipsum description")
                .sessionDomain(SessionDomain.builder()
                        .id(1L)
                        .closeDate(LocalDateTime.now().plusMinutes(10))
                        .createdAt(LocalDateTime.now().minusMinutes(10))
                        .build())
                .build();

        when(agendaUseCase.searchForAnAgenda(agendaId)).thenReturn(agendaDomain);
        when(associateUseCase.getCPF(associateId)).thenReturn(cpf);
        when(associateUseCase.getStatus(cpf)).thenReturn(associateStatus);

        voteUseCase.voteOnTheAgenda(associateId, agendaId, voteDecisionEnumeration);

        verify(agendaUseCase).validateIfYouHaveSessionCreatedOnTheAgenda(any(AgendaDomain.class));
        verify(sessionUseCase).validateIfAgendaIsCloseSession(any(SessionDomain.class));
        verify(voteGateway).save(any(VoteDomain.class));
    }


    @Test
    void must_result_of_the_vote_on_the_agenda() {
        final Long agendaId = 1L;
        final var agendaDomain = AgendaDomain.builder()
                .id(agendaId)
                .createdAt(LocalDateTime.now().minusMinutes(10))
                .title("Lorem Ipsum")
                .description("Lorem Ipsum description")
                .sessionDomain(SessionDomain.builder()
                        .id(1L)
                        .closeDate(LocalDateTime.now().plusMinutes(10))
                        .createdAt(LocalDateTime.now().minusMinutes(10))
                        .build())
                .build();

        when(agendaUseCase.searchForAnAgenda(agendaId)).thenReturn(agendaDomain);
        when(voteGateway.getResult(agendaDomain)).thenReturn(mock(VoteResultDomain.class));

        voteUseCase.resultOfTheVoteOnTheAgenda(agendaId);

        verify(agendaUseCase).validateIfYouHaveSessionCreatedOnTheAgenda(any(AgendaDomain.class));
        verify(sessionUseCase).validateIfAgendaIsOpenSession(any(SessionDomain.class));
        verify(voteGateway).getResult(any(AgendaDomain.class));
    }
}