package com.joao.dataprovider.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record VoteInDTO(@NotBlank String AssociateId, @NotNull Long AgendaId, @NotNull Long SessionId) {
}
