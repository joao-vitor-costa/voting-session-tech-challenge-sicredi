package com.joao.dataprovider.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;


public record OpenVotingSessionInDTO(@NotNull UUID agendaId, Long sessionTime) {

}
