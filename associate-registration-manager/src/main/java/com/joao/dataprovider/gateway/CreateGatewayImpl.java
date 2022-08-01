package com.joao.dataprovider.gateway;

import com.joao.core.domain.AssociateDomain;
import com.joao.core.exception.NotFoundException;
import com.joao.core.gateway.CreateGateway;
import com.joao.dataprovider.mapper.AssociateMapper;
import com.joao.dataprovider.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.joao.core.enumeration.ExceptionCode.ASSOCIATE_NOT_FOUND;

@Service
@RequiredArgsConstructor
class CreateGatewayImpl implements CreateGateway {

    private final AssociateRepository associateRepository;
    private final AssociateMapper associateMapper;


    @Override
    public AssociateDomain create(AssociateDomain associateDomain) {
        return associateMapper.toDomain(associateRepository.save(associateMapper.toEntity(associateDomain)));

    }

    @Override
    public AssociateDomain findById(String id) {
        return Optional.of(associateRepository.findById(id))
                .stream()
                .filter(Optional::isPresent)
                .map(entity -> associateMapper.toDomain(entity.get()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ASSOCIATE_NOT_FOUND));

    }
}
