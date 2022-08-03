package com.joao.dataprovider.dto;

import com.joao.core.enumeration.VoteDecisionEnumeration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record VoteInDTO(@NotBlank String associateId, @NotNull UUID agendaId, @NotNull VoteDecisionEnumeration voteDecision) {
}
