package com.joao.dataprovider.gateway;

import com.joao.EntityBase;
import com.joao.core.domain.AssociateDomain;
import com.joao.dataprovider.entity.AssociateEntity;
import com.joao.dataprovider.mapper.AssociateMapper;
import com.joao.dataprovider.repository.AssociateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith ( MockitoExtension.class )
class AssociateGatewayImplTest extends EntityBase {



    @InjectMocks
    private AssociateGatewayImpl associateGateway;

    @Mock
    private AssociateRepository associateRepository;

    @Mock
    private AssociateMapper associateMapper;

    @Test
    void should_create_associate() {
        final var associateDomain = generateAssociateDomain();
        final var associateEntity = generateAssociateEntity();

        when(associateMapper.toEntity(associateDomain)).thenReturn(associateEntity);
        when(associateRepository.save(associateEntity)).thenReturn(associateEntity);
        when(associateMapper.toDomain(associateEntity)).thenReturn(associateDomain.toBuilder().id(ID).build());

        final var associateCreated = associateGateway.create(associateDomain);

        assertTrue(Objects.nonNull(associateCreated));
        assertEquals(ID, associateCreated.getId());
        verify(associateMapper).toEntity(any(AssociateDomain.class));
        verify(associateRepository).save(any(AssociateEntity.class));
        verify(associateMapper).toDomain(any(AssociateEntity.class));
    }

    @Test
    void must_get_an_associate_by_id() {
        final var associateDomain = generateAssociateDomain();
        final var associateEntity = generateAssociateEntity();

        when(associateRepository.findById(ID)).thenReturn(Optional.of(associateEntity));
        when(associateMapper.toDomain(associateEntity)).thenReturn(associateDomain.toBuilder().id(ID).build());

        final var associateResult = associateGateway.findById(ID);

        assertTrue(Objects.nonNull(associateResult));
        assertEquals(ID, associateResult.getId());
        verify(associateRepository).findById(anyString());
        verify(associateMapper).toDomain(any(AssociateEntity.class));
    }


}