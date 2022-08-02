package com.joao.dataprovider.dto;

import com.joao.core.enumeration.VoteResultEnumeration;

public record VoteResultOutDTO(Long totalNo, Long totalYes, VoteResultEnumeration voteResultEnumeration, Long agendaId, String title,
                               String description) {
}
