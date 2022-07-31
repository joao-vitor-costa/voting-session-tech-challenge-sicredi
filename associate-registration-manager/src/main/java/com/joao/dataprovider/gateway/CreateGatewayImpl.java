package com.joao.dataprovider.gateway;

import com.joao.core.domain.AssociateDomain;
import com.joao.core.exception.NotFoundException;
import com.joao.core.gateway.CreateGateway;
import com.joao.dataprovider.entity.AssociateEntity;
import com.joao.dataprovider.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.joao.core.enumeration.ExceptionCode.ASSOCIATE_NOT_FOUND;

@Service
@RequiredArgsConstructor
class CreateGatewayImpl implements CreateGateway {

    private final AssociateRepository associateRepository;


    @Override
    public AssociateDomain create(AssociateDomain associateDomain) {
        final var entity = associateRepository.save(AssociateEntity.generateBuilder(associateDomain));
        return AssociateDomain.builder()
                .id(entity.id())
                .name(entity.name())
                .cpf(entity.cpf())
                .build();

    }

    @Override
    public AssociateDomain findById(String id) {
        return Optional.of(associateRepository.findById(id))
                .stream()
                .filter(Optional::isPresent)
                .map(entity -> AssociateDomain.builder()
                        .id(entity.get().id())
                        .name(entity.get().name())
                        .cpf(entity.get().cpf())
                        .build())
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ASSOCIATE_NOT_FOUND));

    }
}
