package com.joao.dataprovider.mapper;

import com.joao.core.domain.AssociateDomain;
import com.joao.dataprovider.dto.AssociateInDTO;
import com.joao.dataprovider.dto.AssociateOutDTO;
import com.joao.dataprovider.entity.AssociateEntity;
import org.mapstruct.Mapper;

@Mapper ( componentModel = "spring" )
public interface AssociateMapper {

    AssociateOutDTO toDTO(final AssociateDomain associateDomain);

    AssociateDomain toDomain(final AssociateInDTO associateDTO);

    AssociateDomain toDomain(final AssociateEntity associateEntity);

    AssociateEntity toEntity(final AssociateDomain associateDomain);
}
