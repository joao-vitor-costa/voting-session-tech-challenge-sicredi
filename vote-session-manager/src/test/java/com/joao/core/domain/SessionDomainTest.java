package com.joao.core.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionDomainTest {

    @Test
    void should_return_true_when_the_session_is_open() {
        assertTrue(SessionDomain.builder().closeDate(LocalDateTime.now().minusMinutes(10)).build().isOpenSession());
    }

    @Test
    void should_return_true_when_the_session_is_close() {
        assertTrue(SessionDomain.builder().closeDate(LocalDateTime.now().plusMinutes(10)).build().isCloseSession());
    }
}
