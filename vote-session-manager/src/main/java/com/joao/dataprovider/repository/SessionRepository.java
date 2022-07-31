package com.joao.dataprovider.repository;

import com.joao.dataprovider.entity.SessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<SessionEntity, Long> {
}
