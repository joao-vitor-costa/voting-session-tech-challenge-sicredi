package com.joao.dataprovider.dto;

import com.joao.core.enumeration.VoteDecisionEnumeration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record VoteInDTO(@NotBlank String associateId, @NotNull Long agendaId, @NotNull VoteDecisionEnumeration voteDecision) {
}
