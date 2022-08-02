package com.joao.core.usecase;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.domain.VoteDomain;
import com.joao.core.domain.VoteResultDomain;
import com.joao.core.enumeration.AssociateStatus;
import com.joao.core.enumeration.VoteDecisionEnumeration;
import com.joao.core.exception.NotEligibleVoteException;
import com.joao.core.exception.SecondVoteAttemptException;
import com.joao.core.gateway.VoteGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.joao.core.enumeration.ExceptionCodeEnumeration.NOT_ENABLED_VOTE;
import static com.joao.core.enumeration.ExceptionCodeEnumeration.SECOND_ATTEMPT_VOTE;

@Component
@Slf4j
@RequiredArgsConstructor
public class VoteUseCase {

    private final AgendaUseCase agendaUseCase;
    private final SessionUseCase sessionUseCase;
    private final AssociateUseCase associateUseCase;
    private final VoteGateway voteGateway;

    @Transactional ( rollbackOn = Exception.class )
    public void voteOnTheAgenda(final String associateId, final Long agendaId, final VoteDecisionEnumeration voteDecisionEnumeration) {
        log.info("searching for agenda information");
        final var agendaDomain = agendaUseCase.searchForAnAgenda(agendaId);
        log.info("validation if a session was created on the agenda");
        agendaUseCase.validateIfYouHaveSessionCreatedOnTheAgenda(agendaDomain);
        log.info("validating if open session on the agenda");
        sessionUseCase.validateIfAgendaIsOpenSession(agendaDomain.getSessionDomain());
        log.info("validating if the member has already voted on the agenda");
        validateIfAssociateVotedOnTheAgenda(associateId, agendaDomain);
        log.info("getting the member's CPF");
        final var associateCpf = associateUseCase.getCPF(associateId);
        log.info("getting user status for voting");
        final var associateStatus = associateUseCase.getStatus(associateCpf);
        log.info("validating if the member's status is valid for voting");
        validateIfTheAssociateCanVote(associateStatus);
        log.info("added member vote on the agenda");
        final var voteDomain = VoteDomain.builder()
                .agendaDomain(agendaDomain)
                .createdAt(LocalDateTime.now())
                .associateId(associateId)
                .voteDecisionEnumeration(voteDecisionEnumeration)
                .build();
        voteGateway.save(voteDomain);
        log.info("successful vote");
    }

    public VoteResultDomain resultOfTheVoteOnTheAgenda(final Long agendaId) {
        log.info("searching for agenda information");
        final var agendaDomain = agendaUseCase.searchForAnAgenda(agendaId);
        return voteGateway.getResult(agendaDomain);
    }

    private void validateIfTheAssociateCanVote(final AssociateStatus associateStatus) {
        if (AssociateStatus.UNABLE_TO_VOTE == associateStatus) {
            throw new NotEligibleVoteException(NOT_ENABLED_VOTE);
        }
    }

    public void validateIfAssociateVotedOnTheAgenda(final String associateId, final AgendaDomain agendaDomain) {
        final var voteDomains = voteGateway.findByAgenda(agendaDomain);
        if (voteDomains.stream().anyMatch(voteDomain -> Objects.equals(voteDomain.getAssociateId(), associateId))) {
            throw new SecondVoteAttemptException(SECOND_ATTEMPT_VOTE);
        }
    }
}
