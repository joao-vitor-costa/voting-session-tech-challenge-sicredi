package com.joao.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
public class AgendaDomain {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private SessionDomain sessionDomain;
}
