package com.joao.dataprovider.mapper;

import com.joao.core.domain.AgendaDomain;
import com.joao.dataprovider.dto.in.AgendaInDTO;
import com.joao.dataprovider.dto.out.AgendaOutDTO;
import com.joao.dataprovider.entity.AgendaEntity;
import org.mapstruct.Mapper;

@Mapper ( componentModel = "spring" )
public interface AgendaMapper {
    AgendaDomain toDomain(final AgendaInDTO agendaInDTO);

    AgendaOutDTO toDTO(final AgendaDomain agendaDomain);

    AgendaEntity ToEntity(final AgendaDomain agendaDomain);

    AgendaDomain toDomain(final AgendaEntity agendaEntity);
}
