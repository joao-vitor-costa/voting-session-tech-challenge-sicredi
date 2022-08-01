package com.joao.dataprovider.dto;

import javax.validation.constraints.NotNull;


public record OpenVotingSessionInDTO(@NotNull Long agendaId, Long sessionTime) {

}
