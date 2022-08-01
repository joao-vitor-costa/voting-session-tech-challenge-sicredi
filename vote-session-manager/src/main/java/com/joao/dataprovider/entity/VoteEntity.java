package com.joao.dataprovider.entity;

import com.joao.core.enumeration.VoteDecision;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "vote")
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "associate_id", unique = true, nullable = false)
    private String associateId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated
    @Column(name = "vote_decision", nullable = false)
    private VoteDecision voteDecision;

    @ManyToOne
    @JoinColumn(name = "agenda_entity_id")
    private AgendaEntity agendaEntity;

}
