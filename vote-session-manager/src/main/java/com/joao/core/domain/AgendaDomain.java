package com.joao.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class AgendaDomain {

    private UUID id;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private SessionDomain sessionDomain;
}
