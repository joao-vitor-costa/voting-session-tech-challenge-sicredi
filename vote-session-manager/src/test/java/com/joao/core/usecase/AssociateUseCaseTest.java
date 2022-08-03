package com.joao.core.usecase;

import com.joao.core.enumeration.AssociateStatus;
import com.joao.core.gateway.AssociateGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith ( MockitoExtension.class )
class AssociateUseCaseTest {

    @InjectMocks
    private AssociateUseCase associateUseCase;

    @Mock
    private AssociateGateway associateGateway;

    @Test
    void must_obtain_the_cpf_of_an_associate() {
        final var associateId = "46521623-8d87-4774-b84e-e24ddd898828";
        final var cpf = "189856191734";

        when(associateGateway.searchCPF(associateId)).thenReturn(cpf);

        assertEquals(cpf, associateUseCase.getCPF(associateId));
    }

    @Test
    void must_obtain_membership_voting_status() {
        final var associateId = "46521623-8d87-4774-b84e-e24ddd898828";
        final var cpf = "189856191734";

        when(associateGateway.getStatus(associateId)).thenReturn(AssociateStatus.ABLE_TO_VOTE);

        assertEquals(AssociateStatus.ABLE_TO_VOTE, associateUseCase.getStatus(associateId));
    }
}