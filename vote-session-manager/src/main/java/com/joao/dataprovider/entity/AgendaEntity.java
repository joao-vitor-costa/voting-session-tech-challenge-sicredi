package com.joao.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "agenda")
public class AgendaEntity {
    @Id
    @Type (type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator (name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne ( orphanRemoval = true )
    @JoinColumn ( name = "session_entity_id" )
    private SessionEntity sessionEntity;

}

