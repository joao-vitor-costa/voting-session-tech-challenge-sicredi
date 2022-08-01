package com.joao.core.domain;

import com.joao.core.enumeration.VoteDecision;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoteDomain {

    private SessionDomain sessionDomain;

    private String associateId;

    private VoteDecision voteDecision;

    private AgendaDomain agendaDomain;
}
