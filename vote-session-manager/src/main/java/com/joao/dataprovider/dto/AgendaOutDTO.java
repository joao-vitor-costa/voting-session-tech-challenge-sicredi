package com.joao.dataprovider.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public record AgendaOutDTO(UUID id, String title, LocalDateTime createdAt) {
}
