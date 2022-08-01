package com.joao.dataprovider.gateway;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.gateway.AgendaGateway;
import com.joao.dataprovider.entity.AgendaEntity;
import com.joao.dataprovider.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<AgendaDomain> findAll(final Pageable pageable) {
        return agendaRepository.findAll(pageable).map(agendaEntity -> AgendaDomain.builder().id(agendaEntity.getId()).createdAt(agendaEntity.getCreatedAt()).description(agendaEntity.getDescription()).title(agendaEntity.getTitle()).build());
    }

    @Override
    public AgendaDomain findById(Long id) {
        return null;
    }


}
