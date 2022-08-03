package com.joao.entrypoint.restapi;

import com.joao.core.usecase.RegistrationManagerUseCase;
import com.joao.dataprovider.dto.AssociateInDTO;
import com.joao.dataprovider.dto.AssociateOutDTO;
import com.joao.dataprovider.mapper.AssociateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/associates")
@RequiredArgsConstructor
public class AssociateControllerImpl implements AssociateController {

    private final RegistrationManagerUseCase registrationManagerUseCase;
    private final AssociateMapper associateMapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssociateOutDTO create(@RequestBody @Valid final AssociateInDTO associateDTO) {
        return associateMapper.toDTO(registrationManagerUseCase.addNewAssociate(associateMapper.toDomain(associateDTO)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AssociateOutDTO findById(@PathVariable("id") final String id) {
        return associateMapper.toDTO(registrationManagerUseCase.searchAssociatedByUniqueIdentifier(id));
    }
}
