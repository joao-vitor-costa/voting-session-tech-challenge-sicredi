package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.in.AgendaInDTO;
import com.joao.dataprovider.dto.out.AgendaOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;

@Tag(name = "Agendas")
public interface AgendaController {

    @Operation(summary = "creates a new agenda for the voting session")
    void create(AgendaInDTO agendaInDTO);

    @Operation(summary = "returns a list of registered staves")
    @Parameter(name = "page", in = QUERY, description = "identify which page you want", example = "1")
    @Parameter(name = "linesPerPage", in = QUERY, description = "how many lines per page", example = "24")
    @Parameter(name = "orderBy", in = QUERY, description = "which attribute will sort", example = "createdAt")
    @Parameter(name = "direction", in = QUERY, description = "whichever direction is in ASC or DESC order", example = "DESC")
    Page<AgendaOutDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
