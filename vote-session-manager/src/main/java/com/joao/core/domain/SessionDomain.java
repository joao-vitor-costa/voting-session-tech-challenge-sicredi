package com.joao.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SessionDomain {

    private AgendaDomain agendaDomain;

    private LocalDateTime closeDate;

    private LocalDateTime createdAt;
}
