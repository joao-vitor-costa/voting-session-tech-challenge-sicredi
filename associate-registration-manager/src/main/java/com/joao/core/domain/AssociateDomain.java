package com.joao.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class AssociateDomain {

    private String id;

    private String name;

    private Long cpf;

}
