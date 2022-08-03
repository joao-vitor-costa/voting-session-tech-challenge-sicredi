package com.joao.entrypoint.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joao.core.domain.AgendaDomain;
import com.joao.core.usecase.AgendaUseCase;
import com.joao.dataprovider.dto.AgendaInDTO;
import com.joao.dataprovider.dto.AgendaOutDTO;
import com.joao.dataprovider.mapper.AgendaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith ( SpringExtension.class )
@WebMvcTest ( controllers = AgendaControllerImpl.class )
class AgendaControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendaUseCase agendaUseCase;

    @MockBean
    private AgendaMapper agendaMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void must_create_a_new_agenda() throws Exception {
        var agendaInDTO = new AgendaInDTO("Lorem Title", "Lorem Description");
        mockMvc.perform(post("/v1/agendas")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(agendaInDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void must_list_the_guidelines() throws Exception {
        final var agendaDomain = AgendaDomain.builder().id(UUID.randomUUID()).build();
        when(agendaUseCase.getAllRegisteredGuidelines(0,24,"createdAt", "DESC")).thenReturn(new PageImpl(List.of(agendaDomain)));
        when(agendaMapper.toDTO(agendaDomain)).thenReturn(new AgendaOutDTO(agendaDomain.getId(), agendaDomain.getTitle(), agendaDomain.getCreatedAt()));
        mockMvc.perform(get("/v1/agendas")
                        .param("page", "0")
                        .param("linesPerPage", "24")
                        .param("orderBy", "createdAt")
                        .param("direction", "DESC")
                        .contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

}