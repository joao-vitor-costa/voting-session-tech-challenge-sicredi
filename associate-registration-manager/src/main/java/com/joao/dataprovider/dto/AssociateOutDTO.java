package com.joao.dataprovider.dto;

import com.joao.core.domain.AssociateDomain;
import lombok.Builder;

@Builder
public record AssociateOutDTO(String id, String name, Long cpf) {


    public static AssociateOutDTO generateBuilder(final AssociateDomain associateDomain){
        return AssociateOutDTO.builder()
                .cpf(associateDomain.getCpf())
                .id(associateDomain.getId())
                .name(associateDomain.getName())
                .build();
    }
}
