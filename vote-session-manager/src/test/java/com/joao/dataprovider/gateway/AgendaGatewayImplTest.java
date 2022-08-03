package com.joao.dataprovider.gateway;

import com.joao.core.domain.AgendaDomain;
import com.joao.dataprovider.entity.AgendaEntity;
import com.joao.dataprovider.mapper.AgendaMapper;
import com.joao.dataprovider.repository.AgendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith ( MockitoExtension.class )
class AgendaGatewayImplTest {

    @InjectMocks
    private AgendaGatewayImpl agendaGateway;

    @Mock
    private AgendaRepository agendaRepository;

    @Mock
    private AgendaMapper agendaMapper;

    @Test
    void must_save_a_agenda() {
        final var agendaDomain = AgendaDomain.builder().build();
        var agendaEntity = new AgendaEntity();

        when(agendaMapper.toEntity(agendaDomain)).thenReturn(agendaEntity);

        agendaGateway.save(agendaDomain);

        verify(agendaRepository).save(any(AgendaEntity.class));
        verify(agendaMapper).toEntity(any(AgendaDomain.class));

    }

    @Test
    void search_for_a_staff_by_id() {
        final var agendaDomain = AgendaDomain.builder().id(1L).build();
        var agendaEntity = new AgendaEntity(1L, null, null, null, null);

        when(agendaMapper.toDomain(agendaEntity)).thenReturn(agendaDomain);
        when(agendaRepository.findById(1L)).thenReturn(Optional.of(agendaEntity));

        agendaGateway.findById(1L);

        verify(agendaRepository).findById(anyLong());
        verify(agendaMapper).toDomain(any(AgendaEntity.class));
    }
}