package com.joao.dataprovider.gateway;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.domain.VoteDomain;
import com.joao.core.domain.VoteResultDomain;
import com.joao.core.enumeration.VoteDecisionEnumeration;
import com.joao.core.enumeration.VoteResultEnumeration;
import com.joao.core.gateway.VoteGateway;
import com.joao.dataprovider.mapper.AgendaMapper;
import com.joao.dataprovider.mapper.VoteMapper;
import com.joao.dataprovider.repository.VoteRepository;
import com.joao.dataprovider.strategy.VoteResultStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class VoteGatewayImpl implements VoteGateway {

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final AgendaMapper agendaMapper;
    private final List<VoteResultStrategy> voteResultStrategies;

    @Override
    public void save(VoteDomain voteDomain) {
        voteRepository.save(voteMapper.toEntity(voteDomain));
    }

    @Override
    public List<VoteDomain> findByAgenda(AgendaDomain agendaDomain) {
        return voteRepository.findByAgendaEntity(agendaMapper.toEntity(agendaDomain))
                .stream()
                .map(voteMapper::toDomain)
                .toList();
    }

    @Override
    public VoteResultDomain getResult(final AgendaDomain agendaDomain) {
        final var votes = findByAgenda(agendaDomain);
        final var totalNo = (Long) votes.stream().filter(vote -> VoteDecisionEnumeration.NAO == vote.getVoteDecisionEnumeration()).count();
        final var totalYes = (Long) votes.stream().filter(vote -> VoteDecisionEnumeration.SIM == vote.getVoteDecisionEnumeration()).count();
        return VoteResultDomain.builder()
                .totalNo(totalNo)
                .totalYes(totalYes)
                .voteResultEnumeration(getDecision(totalNo, totalYes))
                .agendaId(agendaDomain.getId())
                .title(agendaDomain.getTitle())
                .description(agendaDomain.getDescription())
                .build();
    }

    private VoteResultEnumeration getDecision(final Long totalNo, final Long totalYes) {
        return voteResultStrategies.stream()
                .filter(strategy -> strategy.toAccept(totalNo, totalYes))
                .map(VoteResultStrategy::getResult)
                .findFirst()
                .orElseThrow();
    }


}
