package com.joao.dataprovider.dto.in;

import javax.validation.constraints.NotNull;

public record OpenVotingSessionInDTO(@NotNull Long AgendaId, @NotNull Long sessionTime) {
}
