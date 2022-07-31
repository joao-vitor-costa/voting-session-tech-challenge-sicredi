package com.joao.dataprovider.gateway;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.gateway.AgendaGateway;
import com.joao.dataprovider.entity.AgendaEntity;
import com.joao.dataprovider.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AgendaGatewayImpl implements AgendaGateway {

    private final AgendaRepository agendaRepository;

    @Override
    public void create(AgendaDomain agendaDomain) {
        agendaRepository.save(AgendaEntity.builder()
                .title(agendaDomain.getTitle())
                .description(agendaDomain.getDescription())
                .createdAt(agendaDomain.getCreatedAt())
                .build());
    }
}
