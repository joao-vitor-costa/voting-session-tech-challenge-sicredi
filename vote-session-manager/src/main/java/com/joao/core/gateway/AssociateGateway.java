package com.joao.core.gateway;

import com.joao.core.enumeration.AssociateStatus;

public interface AssociateGateway {

    String searchCPF(String associateId);

    AssociateStatus getStatus(String associateCpf);
}
