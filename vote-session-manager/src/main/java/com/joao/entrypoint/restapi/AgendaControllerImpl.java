package com.joao.entrypoint.restapi;

import com.joao.core.domain.AgendaDomain;
import com.joao.core.usecase.AgendaUseCase;
import com.joao.dataprovider.dto.in.AgendaInDTO;
import com.joao.dataprovider.dto.out.AgendaOutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @GetMapping("/page")
    public List<AgendaOutDTO> getAllAgenda() {
        return null;
    }
}
