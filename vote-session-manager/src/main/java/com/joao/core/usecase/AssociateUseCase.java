package com.joao.core.usecase;

import com.joao.core.enumeration.AssociateStatus;
import com.joao.core.gateway.AssociateGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AssociateUseCase {

    private final AssociateGateway associateGateway;

    public String getCPF(String associateId) {
        log.info("fetching information from the associate = {}", associateId);
        return associateGateway.searchCPF(associateId);
    }

    public AssociateStatus getStatus(String associateCpf) {
        return associateGateway.getStatus(associateCpf);
    }
}
