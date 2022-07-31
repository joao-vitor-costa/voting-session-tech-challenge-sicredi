package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.AssociateInDTO;
import com.joao.dataprovider.dto.AssociateOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Tag(name = "Associates")
public interface AssociateController {

    @Operation(summary = "insert a new member record")
    AssociateOutDTO create(AssociateInDTO associateDTO);

    @Operation(summary = "get the information of an associate by id")
    @Parameter(name = "id", in = PATH, required = true, description = "unique member identifier", example = "62e5c056c93bba54c5c3ff3a")
    AssociateOutDTO findById(String id);
}
