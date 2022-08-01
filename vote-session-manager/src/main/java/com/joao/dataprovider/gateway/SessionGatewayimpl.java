package com.joao.dataprovider.gateway;

import com.joao.core.domain.SessionDomain;
import com.joao.core.gateway.SessionGateway;
import com.joao.dataprovider.mapper.SessionMapper;
import com.joao.dataprovider.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SessionGatewayimpl implements SessionGateway {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    @Override
    public void create(SessionDomain sessionDomain) {
        sessionRepository.save(sessionMapper.toEntity(sessionDomain));
    }
}
