package com.joao.dataprovider.dto.out;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AgendaOutDTO(Long id, String title, LocalDateTime createdAt) {
}
