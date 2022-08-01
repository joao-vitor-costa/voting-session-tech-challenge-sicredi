package com.joao.dataprovider.dto;

import java.time.LocalDateTime;


public record AgendaOutDTO(Long id, String title, LocalDateTime createdAt) {
}
