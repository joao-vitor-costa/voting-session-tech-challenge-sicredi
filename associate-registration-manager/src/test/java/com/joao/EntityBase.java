package com.joao;

import com.joao.core.domain.AssociateDomain;
import com.joao.dataprovider.entity.AssociateEntity;

import java.time.LocalDateTime;

public abstract class EntityBase {

    public final static String ID = "234567890";
    public final static String NAME = "Joao";
    public final static Long CPF = 41582419280L;
    public final static LocalDateTime CREATED_AT = LocalDateTime.now();


    public AssociateDomain generateAssociateDomain() {
        return AssociateDomain.builder()
                .name(NAME)
                .cpf(CPF)
                .createdAt(CREATED_AT)
                .build();
    }

    public AssociateEntity generateAssociateEntity() {
        return new AssociateEntity(ID, NAME, CPF, CREATED_AT);
    }
}
