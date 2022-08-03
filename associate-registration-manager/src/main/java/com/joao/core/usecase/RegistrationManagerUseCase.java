package com.joao.core.usecase;

import com.joao.core.domain.AssociateDomain;
import com.joao.core.gateway.AssociateGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationManagerUseCase {

    private final AssociateGateway associateGateway;


    public AssociateDomain addNewAssociate(final AssociateDomain associateDomain) {
        log.info("requesting membership creation");
        return associateGateway.create(associateDomain);
    }

    public AssociateDomain searchAssociatedByUniqueIdentifier(final String id) {
        log.info("looking for associate that has the id={}", id);
        return associateGateway.findById(id);
    }
}
