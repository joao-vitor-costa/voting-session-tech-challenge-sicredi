package com.joao.entrypoint.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joao.core.usecase.RegistrationManagerUseCase;
import com.joao.dataprovider.dto.AssociateInDTO;
import com.joao.dataprovider.mapper.AssociateMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith ( SpringExtension.class )
@WebMvcTest ( controllers = AssociateControllerImpl.class )
class AssociateControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationManagerUseCase registrationManagerUseCase;

    @MockBean
    private AssociateMapper associateMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void must_create_associate() throws Exception {
        var associateInDTO = new AssociateInDTO("Lorem name", 32933320215L);
        mockMvc.perform(post("/v1/associates")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(associateInDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void should_return_associate_by_id() throws Exception {
        mockMvc.perform(get("/v1/associates/{id}", 1L)
                        .contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}