package com.joao.entrypoint.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joao.core.enumeration.VoteDecisionEnumeration;
import com.joao.core.usecase.SessionUseCase;
import com.joao.core.usecase.VoteUseCase;
import com.joao.dataprovider.dto.OpenVotingSessionInDTO;
import com.joao.dataprovider.dto.VoteInDTO;
import com.joao.dataprovider.mapper.VoteMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith ( SpringExtension.class )
@WebMvcTest ( controllers = VoteControllerImpl.class )
class VoteControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  VoteUseCase voteUseCase;

    @MockBean
    private  VoteMapper voteMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_give_successful_return_the_vote() throws Exception {
        var voteInDTO = new VoteInDTO("46521623-8d87-4774-b84e-e24ddd898828", UUID.randomUUID(), VoteDecisionEnumeration.SIM);

        mockMvc.perform(post("/v1/votes")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(voteInDTO)))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void should_return_the_result_oft_he_votes() throws Exception {
        mockMvc.perform(get("/v1/votes/total-votes/{agenda_id}/agenda-id", 1L)
                        .contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }
}