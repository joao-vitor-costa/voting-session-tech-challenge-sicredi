package com.joao.core.domain;

import com.joao.core.enumeration.VoteDecisionEnumeration;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class VoteDomain {

    private Long id;

    private String associateId;

    private VoteDecisionEnumeration voteDecisionEnumeration;

    private AgendaDomain agendaDomain;

    private LocalDateTime createdAt;

}
