package com.joao.core.gateway;

import com.joao.core.domain.SessionDomain;

public interface SessionGateway {
    void create(final SessionDomain sessionDomain);
}
