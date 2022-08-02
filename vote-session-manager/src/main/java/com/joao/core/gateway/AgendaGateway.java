package com.joao.core.gateway;

import com.joao.core.domain.AgendaDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgendaGateway {

    void save(AgendaDomain agendaDomain);

    Page<AgendaDomain> findAll(Pageable pageable);

    Optional<AgendaDomain> findById(Long id);
}
