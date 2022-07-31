package com.joao.core.domain;

import com.joao.dataprovider.entity.AgendaEntity;
import com.joao.dataprovider.entity.VoteEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class SessionDomain {

    private Long id;

    private AgendaEntity agendaEntity;

    private Set<VoteEntity> voteEntities;

    private Long sessionTime;
}
