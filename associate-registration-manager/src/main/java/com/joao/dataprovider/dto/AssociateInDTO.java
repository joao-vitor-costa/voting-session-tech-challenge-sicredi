package com.joao.dataprovider.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AssociateInDTO(@NotBlank String name, @NotNull Long cpf) {
}
