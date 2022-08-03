package com.joao.dataprovider.gateway;

import com.joao.core.enumeration.AssociateStatus;
import com.joao.dataprovider.client.AssociateClient;
import com.joao.dataprovider.client.AssociateInfoStatusClient;
import com.joao.dataprovider.dto.AssociateOutDTO;
import com.joao.dataprovider.dto.AssociateValidOutDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith ( MockitoExtension.class )
class AssociateGatewayImplTest {

    @InjectMocks
    private AssociateGatewayImpl associateGateway;

    @Mock
    private AssociateClient associateClient;

    @Mock
    private AssociateInfoStatusClient associateInfoStatusClient;

    @Test
    void must_search_cpf() {
        final var associateId = "46521623-8d87-4774-b84e-e24ddd898828";
        final var cpf = 189856191734L;
        var associateOutDTO = new AssociateOutDTO(associateId, "Joao", cpf);

        when(associateClient.getAssociate(associateId)).thenReturn(associateOutDTO);

        final var associateCpf = associateGateway.searchCPF(associateId);

        assertEquals(Long.toString(cpf), associateCpf);
        verify(associateClient).getAssociate(any());
    }

    @Test
    void must_get_status() {
        final var cpf = "189856191734";
        var status = new AssociateValidOutDTO(AssociateStatus.ABLE_TO_VOTE);

        when(associateInfoStatusClient.getInformationAssociateIsValid(cpf)).thenReturn(status);

        final var associateStatus = associateGateway.getStatus(cpf);

        assertEquals(AssociateStatus.ABLE_TO_VOTE, associateStatus);
        verify(associateInfoStatusClient).getInformationAssociateIsValid(any());
    }
}