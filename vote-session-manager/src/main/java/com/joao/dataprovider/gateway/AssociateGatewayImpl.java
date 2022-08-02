package com.joao.dataprovider.gateway;

import com.joao.core.enumeration.AssociateStatus;
import com.joao.core.gateway.AssociateGateway;
import com.joao.dataprovider.client.AssociateClient;
import com.joao.dataprovider.client.AssociateInfoStatusClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AssociateGatewayImpl implements AssociateGateway {

    private final AssociateClient associateClient;
    private final AssociateInfoStatusClient associateInfoStatusClient;

    @Override
    public String searchCPF(final String associateId) {
        return associateClient.getAssociate(associateId).cpf().toString();
    }

    @Override
    public AssociateStatus getStatus(final String associateCpf) {
        return associateInfoStatusClient.getInformationAssociateIsValid(associateCpf).status();
    }
}
