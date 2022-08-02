package com.joao.dataprovider.mapper;

import com.joao.core.domain.AgendaDomain;
import com.joao.dataprovider.dto.AgendaInDTO;
import com.joao.dataprovider.dto.AgendaOutDTO;
import com.joao.dataprovider.entity.AgendaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper ( componentModel = "spring" )
public interface AgendaMapper {
    AgendaDomain toDomain(AgendaInDTO agendaInDTO);

    AgendaOutDTO toDTO(AgendaDomain agendaDomain);

    @Mapping ( target = "sessionEntity", source = "sessionDomain" )
    AgendaEntity ToEntity(AgendaDomain agendaDomain);

    @Mapping ( target = "sessionDomain", source = "sessionEntity" )
    AgendaDomain toDomain(AgendaEntity agendaEntity);
}
