package com.joao.entrypoint.restapi;

import com.joao.core.usecase.AgendaUseCase;
import com.joao.dataprovider.dto.AgendaInDTO;
import com.joao.dataprovider.dto.AgendaOutDTO;
import com.joao.dataprovider.mapper.AgendaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ( "/v1/agendas" )
@RequiredArgsConstructor
public class AgendaControllerImpl implements AgendaController {

    private final AgendaUseCase agendaUseCase;
    private final AgendaMapper agendaMapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final AgendaInDTO agendaInDTO) {
        agendaUseCase.newAgenda(agendaMapper.toDomain(agendaInDTO));
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AgendaOutDTO> findPage(@RequestParam(value = "page", defaultValue = "0") final Integer page,
                                       @RequestParam(value = "linesPerPage", defaultValue = "24") final Integer linesPerPage,
                                       @RequestParam(value = "orderBy", defaultValue = "createdAt") final String orderBy,
                                       @RequestParam(value = "direction", defaultValue = "DESC") final String direction) {
        return agendaUseCase.getAllRegisteredGuidelines(page, linesPerPage, orderBy, direction)
                .map(agendaMapper::toDTO);

    }
}
