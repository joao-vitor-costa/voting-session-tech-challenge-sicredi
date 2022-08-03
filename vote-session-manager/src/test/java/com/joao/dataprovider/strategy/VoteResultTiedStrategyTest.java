package com.joao.dataprovider.strategy;

import com.joao.core.enumeration.VoteResultEnumeration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith ( MockitoExtension.class )
class VoteResultTiedStrategyTest {
    @InjectMocks
    private VoteResultTiedStrategy strategy;

    @Test
    void should_accept() {
        assertTrue(strategy.toAccept(10L, 10L));
    }

    @Test
    void should_not_accept() {
        assertFalse(strategy.toAccept(100L, 2L));

    }

    @Test
    void should_get_result() {
        assertEquals(VoteResultEnumeration.TIED, strategy.getResult());
    }
}