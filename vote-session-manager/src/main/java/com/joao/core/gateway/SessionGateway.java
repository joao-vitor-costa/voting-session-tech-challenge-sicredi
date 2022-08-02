package com.joao.core.gateway;

import com.joao.core.domain.SessionDomain;

public interface SessionGateway {
    SessionDomain save(final SessionDomain sessionDomain);
}
