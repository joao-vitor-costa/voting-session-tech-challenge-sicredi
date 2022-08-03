package com.joao.dataprovider.gateway;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.domain.SessionDomain;
import com.joao.core.domain.VoteDomain;
import com.joao.core.enumeration.VoteDecisionEnumeration;
import com.joao.core.enumeration.VoteResultEnumeration;
import com.joao.dataprovider.entity.AgendaEntity;
import com.joao.dataprovider.entity.SessionEntity;
import com.joao.dataprovider.entity.VoteEntity;
import com.joao.dataprovider.mapper.AgendaMapper;
import com.joao.dataprovider.mapper.VoteMapper;
import com.joao.dataprovider.repository.VoteRepository;
import com.joao.dataprovider.strategy.VoteResultApprovedStrategy;
import com.joao.dataprovider.strategy.VoteResultDeniedStrategy;
import com.joao.dataprovider.strategy.VoteResultStrategy;
import com.joao.dataprovider.strategy.VoteResultTiedStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith ( MockitoExtension.class )
class VoteGatewayImplTest {

    @InjectMocks
    private VoteGatewayImpl voteGateway;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VoteMapper voteMapper;

    @Mock
    private AgendaMapper agendaMapper;

    @Spy
    private List<VoteResultStrategy> voteResultStrategies = new ArrayList<>();


    @BeforeEach
    void init() {
        voteResultStrategies.addAll(List.of(new VoteResultApprovedStrategy(), new VoteResultDeniedStrategy(), new VoteResultTiedStrategy()));
    }

    @Test
    void must_save_vote() {
        final var agendaId = UUID.randomUUID();
        final var voteId = UUID.randomUUID();
        final var agendaDomain = generateAgendaDomain(agendaId);
        final var voteDomain = generateVoteDomain(agendaDomain, voteId);
        final var agendaEntity = generateAgendaEntity(agendaId);
        final var voteEntity = generateVoteEntity(agendaEntity, voteId);

        when(voteMapper.toEntity(voteDomain)).thenReturn(voteEntity);

        voteGateway.save(voteDomain);

        verify(voteMapper).toEntity(any(VoteDomain.class));
        verify(voteRepository).save(any(VoteEntity.class));
    }

    @Test
    void must_find_by_agenda() {
        final var agendaId = UUID.randomUUID();
        final var voteId = UUID.randomUUID();
        final var agendaDomain = generateAgendaDomain(agendaId);
        final var voteDomain = generateVoteDomain(agendaDomain, voteId);
        final var agendaEntity = generateAgendaEntity(agendaId);
        final var voteEntity = generateVoteEntity(agendaEntity, voteId);
        final var votes = List.of(voteEntity);

        when(agendaMapper.toEntity(agendaDomain)).thenReturn(agendaEntity);
        when(voteRepository.findByAgendaEntity(agendaEntity)).thenReturn(votes);
        when(voteMapper.toDomain(voteEntity)).thenReturn(voteDomain);

        voteGateway.findByAgenda(agendaDomain);

        verify(agendaMapper).toEntity(any(AgendaDomain.class));
        verify(voteRepository).findByAgendaEntity(any(AgendaEntity.class));
        verify(voteMapper).toDomain(any(VoteEntity.class));
    }

    @Test
    void must_get_the_result_of_the_vote() {
        final var agendaId = UUID.randomUUID();
        final var voteId = UUID.randomUUID();
        final var agendaDomain = generateAgendaDomain(agendaId);
        final var voteDomain = generateVoteDomain(agendaDomain, voteId);
        final var agendaEntity = generateAgendaEntity(agendaId);
        final var voteEntity = generateVoteEntity(agendaEntity, voteId);
        final var votes = List.of(voteEntity);

        when(agendaMapper.toEntity(agendaDomain)).thenReturn(agendaEntity);
        when(voteRepository.findByAgendaEntity(agendaEntity)).thenReturn(votes);
        when(voteMapper.toDomain(voteEntity)).thenReturn(voteDomain);

        final var result = voteGateway.getResult(agendaDomain);

        assertTrue(Objects.nonNull(result));
        assertEquals(VoteResultEnumeration.APPROVED, result.getVoteResultEnumeration());
        verify(agendaMapper).toEntity(any(AgendaDomain.class));
        verify(voteRepository).findByAgendaEntity(any(AgendaEntity.class));
        verify(voteMapper).toDomain(any(VoteEntity.class));

    }

    private AgendaDomain generateAgendaDomain(final UUID id) {
        return AgendaDomain.builder()
                .id(id)
                .title("Lorem")
                .description("Lorem")
                .createdAt(LocalDateTime.now())
                .sessionDomain(SessionDomain.builder().id(id).build())
                .build();
    }

    private VoteDomain generateVoteDomain(final AgendaDomain agendaDomain, UUID id) {
        return VoteDomain.builder()
                .id(id)
                .associateId("34567890")
                .agendaDomain(agendaDomain)
                .voteDecisionEnumeration(VoteDecisionEnumeration.SIM)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private AgendaEntity generateAgendaEntity(UUID id) {
        return AgendaEntity.builder()
                .id(id)
                .title("Lorem")
                .description("Lorem")
                .createdAt(LocalDateTime.now())
                .sessionEntity(SessionEntity.builder().id(id).build())
                .build();
    }

    private VoteEntity generateVoteEntity(final AgendaEntity agendaEntity, UUID id) {
        return VoteEntity.builder()
                .id(id)
                .associateId("34567890")
                .agendaEntity(agendaEntity)
                .voteDecisionEnumeration(VoteDecisionEnumeration.SIM)
                .createdAt(LocalDateTime.now())
                .build();
    }
}