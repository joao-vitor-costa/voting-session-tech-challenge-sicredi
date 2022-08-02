package com.joao.dataprovider.mapper;

import com.joao.core.domain.VoteDomain;
import com.joao.core.domain.VoteResultDomain;
import com.joao.dataprovider.dto.VoteResultOutDTO;
import com.joao.dataprovider.entity.VoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper ( componentModel = "spring" )
public interface VoteMapper {
    VoteResultOutDTO toDTO(VoteResultDomain voteResultDomain);
    @Mapping (target = "agendaEntity", source = "agendaDomain")
    VoteEntity toEntity(VoteDomain voteDomain);

    @Mapping (target = "agendaDomain", source = "agendaEntity")
    VoteDomain toDomain(VoteEntity voteEntity);
}
