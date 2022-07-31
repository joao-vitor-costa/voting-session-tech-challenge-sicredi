package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.in.AgendaInDTO;
import com.joao.dataprovider.dto.out.AgendaOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Guidelines")
public interface AgendaController {

    @Operation(summary = "creates a new agenda for the voting session")
    void create(AgendaInDTO agendaInDTO);

    @Operation(summary = "returns a list of registered staves")
    List<AgendaOutDTO> getAllAgenda();
}
