package com.joao.core.domain;

import com.joao.core.enumeration.VoteDecisionEnumeration;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoteDomain {

    private SessionDomain sessionDomain;

    private String associateId;

    private VoteDecisionEnumeration voteDecisionEnumeration;

    private AgendaDomain agendaDomain;
}
