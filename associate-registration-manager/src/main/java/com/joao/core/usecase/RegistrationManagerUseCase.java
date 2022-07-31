package com.joao.core.usecase;

import com.joao.core.domain.AssociateDomain;
import com.joao.core.gateway.CreateGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationManagerUseCase {

    private final CreateGateway createGateway;


    public AssociateDomain addNewAssociate(final AssociateDomain associateDomain) {
        log.info("requesting membership creation");
        return createGateway.create(associateDomain);
    }

    public AssociateDomain searchAssociatedByUniqueIdentifier(final String id) {
        log.info("looking for associate that has the id={}", id);
        return createGateway.findById(id);
    }
}
