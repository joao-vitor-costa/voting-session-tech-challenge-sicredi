package com.joao.core.domain;

import com.joao.core.enumeration.VoteDecisionEnumeration;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class VoteDomain {

    private UUID id;

    private String associateId;

    private VoteDecisionEnumeration voteDecisionEnumeration;

    private AgendaDomain agendaDomain;

    private LocalDateTime createdAt;

}
