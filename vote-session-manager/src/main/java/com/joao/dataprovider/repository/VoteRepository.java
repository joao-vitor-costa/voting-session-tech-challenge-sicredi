package com.joao.dataprovider.repository;

import com.joao.dataprovider.entity.AgendaEntity;
import com.joao.dataprovider.entity.VoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, Long> {
    List<VoteEntity> findByAgendaEntity(AgendaEntity agendaEntity);
}
