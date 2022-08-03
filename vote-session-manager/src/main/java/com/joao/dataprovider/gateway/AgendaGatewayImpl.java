package com.joao.dataprovider.gateway;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.gateway.AgendaGateway;
import com.joao.dataprovider.mapper.AgendaMapper;
import com.joao.dataprovider.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class AgendaGatewayImpl implements AgendaGateway {

    private final AgendaRepository agendaRepository;
    private final AgendaMapper agendaMapper;

    @Override
    public void save(AgendaDomain agendaDomain) {
        agendaRepository.save(agendaMapper.toEntity(agendaDomain));
    }

    @Override
    public Page<AgendaDomain> findAll(final Pageable pageable) {
        return agendaRepository.findAll(pageable)
                .map(agendaMapper::toDomain);
    }

    @Override
    public Optional<AgendaDomain> findById(Long id) {
        return agendaRepository.findById(id)
                .map(agendaMapper::toDomain);

    }


}
