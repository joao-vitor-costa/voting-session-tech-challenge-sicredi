package com.joao.dataprovider.repository;

import com.joao.dataprovider.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {
    Optional<AgendaEntity> findById(UUID id);
}
