package com.joao.dataprovider.repository;

import com.joao.dataprovider.entity.AssociateEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends MongoRepository<AssociateEntity, String> {
}
