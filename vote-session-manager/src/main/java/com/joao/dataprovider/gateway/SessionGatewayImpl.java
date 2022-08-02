package com.joao.dataprovider.gateway;

import com.joao.core.domain.SessionDomain;
import com.joao.core.gateway.SessionGateway;
import com.joao.dataprovider.mapper.SessionMapper;
import com.joao.dataprovider.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SessionGatewayImpl implements SessionGateway {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    @Override
    public SessionDomain save(SessionDomain sessionDomain) {
        return sessionMapper.toDomain(sessionRepository.save(sessionMapper.toEntity(sessionDomain)));
    }
}
