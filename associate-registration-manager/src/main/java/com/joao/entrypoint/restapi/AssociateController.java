package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.AssociateInDTO;
import com.joao.dataprovider.dto.AssociateOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Tag ( name = "Associates" )
public interface AssociateController {

    @Operation ( summary = "insert a new member record" )
    @ApiResponse ( responseCode = "201", description = "Created", content = @Content ( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema ( implementation = AssociateOutDTO.class ) ) )
    AssociateOutDTO create(AssociateInDTO associateDTO);

    @Operation ( summary = "get the information of an associate by id" )
    @Parameter ( name = "id", in = PATH, required = true, description = "unique member identifier", example = "62e5c056c93bba54c5c3ff3a" )
    @ApiResponse ( responseCode = "200", description = "OK", content = @Content ( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema ( implementation = AssociateOutDTO.class ) ) )
    AssociateOutDTO findById(String id);
}
