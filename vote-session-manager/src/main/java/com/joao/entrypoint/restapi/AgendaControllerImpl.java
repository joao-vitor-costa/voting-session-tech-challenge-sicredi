package com.joao.entrypoint.restapi;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.usecase.AgendaUseCase;
import com.joao.dataprovider.dto.in.AgendaInDTO;
import com.joao.dataprovider.dto.out.AgendaOutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/guidelines")
@RequiredArgsConstructor
public class AgendaControllerImpl implements AgendaController {

    private final AgendaUseCase agendaUseCase;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final AgendaInDTO agendaInDTO) {
        agendaUseCase.newAgenda(AgendaDomain.builder()
                .title(agendaInDTO.title())
                .description(agendaInDTO.description())
                .build());
    }

    @Override
    @GetMapping
    public Page<AgendaOutDTO> findPage(@RequestParam(value = "page", defaultValue = "0") final Integer page,
                                       @RequestParam(value = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
                                       @RequestParam(value = "orderBy", defaultValue = "createdAt") final String orderBy,
                                       @RequestParam(value = "direction", defaultValue = "DESC") final String direction) {
        return agendaUseCase.getAllRegisteredGuidelines(page, linesPerPage, orderBy, direction)
                .map(agendaDomain -> AgendaOutDTO.builder()
                        .id(agendaDomain.getId())
                        .title(agendaDomain.getTitle())
                        .createdAt(agendaDomain.getCreatedAt())
                        .build());

    }
}
