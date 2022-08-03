package com.joao.dataprovider.entity;

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
@Table ( name = "session" )
public class SessionEntity {
    @Id
    @Type ( type = "uuid-char" )
    @GeneratedValue ( generator = "uuid2" )
    @GenericGenerator ( name = "uuid2", strategy = "uuid2" )
    private UUID id;

    @Column ( name = "close_date", nullable = false )
    private LocalDateTime closeDate;

    @Column ( name = "created_at", nullable = false )
    private LocalDateTime createdAt;


}
