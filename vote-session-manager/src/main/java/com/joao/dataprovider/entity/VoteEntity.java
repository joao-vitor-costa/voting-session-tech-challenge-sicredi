package com.joao.dataprovider.entity;

import com.joao.core.enumeration.VoteDecisionEnumeration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "vote")
public class VoteEntity {

    @Id
    @Type (type = "uuid-char")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator (name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "associate_id", unique = true, nullable = false)
    private String associateId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated
    @Column(name = "vote_decision", nullable = false)
    private VoteDecisionEnumeration voteDecisionEnumeration;

    @ManyToOne
    @JoinColumn ( name = "agenda_entity_id" )
    private AgendaEntity agendaEntity;

}
