package com.joao.dataprovider.entity;

import com.joao.core.domain.AssociateDomain;
import com.joao.dataprovider.dto.AssociateOutDTO;
import lombok.Builder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Document(collection = "associate")
public record AssociateEntity(String id, String name, @Indexed(unique = true) Long cpf) {

    public static AssociateEntity generateBuilder(final AssociateDomain associateDomain){
        return AssociateEntity.builder()
                .cpf(associateDomain.getCpf())
                .id(associateDomain.getId())
                .name(associateDomain.getName())
                .build();
    }

}
