package com.joao.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class SessionDomain {
    private UUID id;
    private LocalDateTime closeDate;

    private LocalDateTime createdAt;

    public boolean isCloseSession() {
        return this.closeDate.isAfter(LocalDateTime.now());
    }

    public boolean isOpenSession() {
        return this.closeDate.isBefore(LocalDateTime.now());
    }
}
