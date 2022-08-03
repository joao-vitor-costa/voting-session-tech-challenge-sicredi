package com.joao.core.usecase;

import com.joao.EntityBase;
import com.joao.core.domain.AssociateDomain;
import com.joao.core.gateway.AssociateGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith ( MockitoExtension.class )
class RegistrationManagerUseCaseTest extends EntityBase {

    @InjectMocks
    private RegistrationManagerUseCase registrationManagerUseCase;

    @Mock
    private AssociateGateway associateGateway;

    @Test
    void should_add_new_associate() {
        final var associateDomain = generateAssociateDomain();

        when(associateGateway.create(associateDomain)).thenReturn(associateDomain.toBuilder().id("4848787").build());

        final var associateCreated = registrationManagerUseCase.addNewAssociate(associateDomain);

        assertTrue(Objects.nonNull(associateCreated));
        verify(associateGateway).create(any(AssociateDomain.class));
    }

    @Test
    void should_search_associated_by_unique_identifier() {
        final var associateDomain = generateAssociateDomain();

        when(associateGateway.findById(ID)).thenReturn(associateDomain);

        final var associateById = registrationManagerUseCase.searchAssociatedByUniqueIdentifier(ID);

        assertTrue(Objects.nonNull(associateById));
        verify(associateGateway).findById(anyString());
    }
}