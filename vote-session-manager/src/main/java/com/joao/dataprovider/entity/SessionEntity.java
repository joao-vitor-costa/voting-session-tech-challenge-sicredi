package com.joao.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "session")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "agenda_entity_id", nullable = false)
    private AgendaEntity agendaEntity;

    @OneToMany(mappedBy = "sessionEntity", orphanRemoval = true)
    private Set<VoteEntity> voteEntities;

    @Column(name = "session_time", nullable = false)
    private Long sessionTime;

}
