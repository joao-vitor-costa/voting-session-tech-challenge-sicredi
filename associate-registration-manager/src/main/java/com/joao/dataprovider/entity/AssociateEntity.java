package com.joao.dataprovider.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "associate")
public record AssociateEntity(String id, String name, @Indexed(unique = true) Long cpf) {
}
