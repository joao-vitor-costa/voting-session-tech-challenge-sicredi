package com.joao.core.domain;

import com.joao.dataprovider.entity.VoteEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
public class AgendaDomain {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private SessionDomain sessionDomain;

    private Set<VoteEntity> voteEntities;

}
