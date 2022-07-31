package com.joao.core.domain;

import com.joao.core.enumeration.VoteDecision;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class VoteDomain {

    private SessionDomain sessionDomain;

    private String associateId;

    private LocalDateTime createdAt;

    private VoteDecision voteDecision;
}
