package com.joao.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SessionDomain {
    private Long id;
    private LocalDateTime closeDate;

    private LocalDateTime createdAt;

    public boolean isCloseSession() {
        return this.closeDate.isAfter(LocalDateTime.now());
    }

    public boolean isOpenSession() {
        return this.closeDate.isBefore(LocalDateTime.now());
    }
}
