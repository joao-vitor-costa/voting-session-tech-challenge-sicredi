package com.joao.core.domain;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AssociateDomain {

    private String id;

    private String name;

    private Long cpf;

}
