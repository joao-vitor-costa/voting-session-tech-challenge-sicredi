package com.joao.dataprovider.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "associate")
public record AssociateEntity(String id, String name, @Indexed(unique = true) Long cpf, LocalDateTime createdAt) {
}
