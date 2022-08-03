package com.joao.entrypoint.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joao.core.usecase.SessionUseCase;
import com.joao.dataprovider.dto.OpenVotingSessionInDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith ( SpringExtension.class )
@WebMvcTest ( controllers = SessionControllerImpl.class )
class SessionControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionUseCase sessionUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_open_voting_session() throws Exception {
        var openVotingSessionInDTO = new OpenVotingSessionInDTO(UUID.randomUUID(), 10L);
        mockMvc.perform(post("/v1/sessions")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(openVotingSessionInDTO)))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}