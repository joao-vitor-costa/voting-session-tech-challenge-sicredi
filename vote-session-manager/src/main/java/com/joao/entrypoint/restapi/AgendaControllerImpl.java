package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.in.AgendaInDTO;
import com.joao.dataprovider.dto.out.AgendaOutDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/guidelines")
public class AgendaControllerImpl implements AgendaController {

    @Override
    @PostMapping
    public void create(@RequestBody @Valid final AgendaInDTO agendaInDTO) {

    }

    @Override
    @GetMapping("/page")
    public List<AgendaOutDTO> getAllAgenda() {
        return null;
    }
}
