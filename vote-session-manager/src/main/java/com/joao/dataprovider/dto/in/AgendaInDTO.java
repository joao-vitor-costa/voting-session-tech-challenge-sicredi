package com.joao.dataprovider.dto.in;

import javax.validation.constraints.NotBlank;

public record AgendaInDTO(@NotBlank String title, @NotBlank String description) {
}
