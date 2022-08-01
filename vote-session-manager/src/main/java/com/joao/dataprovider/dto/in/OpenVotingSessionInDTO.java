package com.joao.dataprovider.dto.in;

import javax.validation.constraints.NotNull;


public record OpenVotingSessionInDTO(@NotNull Long agendaId, @NotNull Long sessionTime) {

}
