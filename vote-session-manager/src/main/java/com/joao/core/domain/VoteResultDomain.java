package com.joao.core.domain;

import com.joao.core.enumeration.VoteResultEnumeration;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class VoteResultDomain {

    private Long totalNo;
    private Long totalYes;
    private VoteResultEnumeration voteResultEnumeration;
    private UUID agendaId;
    private String title;
    private String description;
}
