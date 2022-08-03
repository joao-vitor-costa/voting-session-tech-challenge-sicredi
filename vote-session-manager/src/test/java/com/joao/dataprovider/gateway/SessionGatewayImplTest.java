package com.joao.dataprovider.gateway;

import com.joao.core.domain.SessionDomain;
import com.joao.dataprovider.entity.SessionEntity;
import com.joao.dataprovider.mapper.SessionMapper;
import com.joao.dataprovider.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith ( MockitoExtension.class )
class SessionGatewayImplTest {
    @InjectMocks
    private SessionGatewayImpl sessionGateway;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private SessionMapper sessionMapper;

    @Test
    void must_save_session() {
        final var sessionDomain = SessionDomain.builder().build();
        var sessionEntity = new SessionEntity();

        when(sessionMapper.toEntity(sessionDomain)).thenReturn(sessionEntity);
        when(sessionRepository.save(sessionEntity)).thenReturn(sessionEntity);
        when(sessionMapper.toDomain(sessionEntity)).thenReturn(sessionDomain);

        sessionGateway.save(sessionDomain);

        verify(sessionMapper).toEntity(any(SessionDomain.class));
        verify(sessionRepository).save(any(SessionEntity.class));
        verify(sessionMapper).toDomain(any(SessionEntity.class));
    }
}