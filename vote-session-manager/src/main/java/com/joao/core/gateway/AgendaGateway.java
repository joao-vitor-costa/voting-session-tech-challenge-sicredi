package com.joao.core.gateway;

import com.joao.core.domain.AgendaDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AgendaGateway {

    void create(AgendaDomain agendaDomain);

    Page<AgendaDomain> findAll(Pageable pageable);
}
