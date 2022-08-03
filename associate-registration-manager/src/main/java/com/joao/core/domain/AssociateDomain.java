package com.joao.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder ( toBuilder = true )
public class AssociateDomain {

    private String id;

    private String name;

    private Long cpf;

    private LocalDateTime createdAt;

}
