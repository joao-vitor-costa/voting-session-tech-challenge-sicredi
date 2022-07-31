package com.joao.dataprovider.dto.in;

import javax.validation.constraints.NotNull;

public record VoteInDTO(@NotNull String AssociateId, @NotNull Long AgendaId, @NotNull Long SessionId) {
}
