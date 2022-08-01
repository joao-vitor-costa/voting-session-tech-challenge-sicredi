package com.joao.dataprovider.dto;

import javax.validation.constraints.NotBlank;

public record AgendaInDTO(@NotBlank String title, @NotBlank String description) {
}
