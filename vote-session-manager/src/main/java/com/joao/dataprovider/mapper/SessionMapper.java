package com.joao.dataprovider.mapper;

import com.joao.core.domain.SessionDomain;
import com.joao.dataprovider.entity.SessionEntity;
import org.mapstruct.Mapper;

@Mapper ( componentModel = "spring" )
public interface SessionMapper {

    SessionEntity toEntity(SessionDomain sessionDomain);

    SessionDomain toDomain(SessionEntity sessionEntity);
}
