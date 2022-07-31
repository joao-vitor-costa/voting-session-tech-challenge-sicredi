package com.joao.entrypoint.restapi;

import com.joao.core.domain.AssociateDomain;
import com.joao.core.usecase.RegistrationManagerUseCase;
import com.joao.dataprovider.dto.AssociateInDTO;
import com.joao.dataprovider.dto.AssociateOutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/associates")
@RequiredArgsConstructor
public class AssociateControllerImpl implements AssociateController {

    private final RegistrationManagerUseCase registrationManagerUseCase;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssociateOutDTO create(@RequestBody @Valid final AssociateInDTO associateDTO) {
        return AssociateOutDTO.generateBuilder(registrationManagerUseCase.addNewAssociate(AssociateDomain.builder()
                .cpf(associateDTO.cpf())
                .name(associateDTO.name())
                .build()));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AssociateOutDTO findById(@PathVariable("id") final String id) {
        return AssociateOutDTO.generateBuilder(registrationManagerUseCase.searchAssociatedByUniqueIdentifier(id));
    }
}
