package com.joao.dataprovider.dto;

import com.joao.core.enumeration.VoteResultEnumeration;

import java.util.UUID;

public record VoteResultOutDTO(Long totalNo, Long totalYes, VoteResultEnumeration voteResultEnumeration, UUID agendaId, String title,
                               String description) {
}
