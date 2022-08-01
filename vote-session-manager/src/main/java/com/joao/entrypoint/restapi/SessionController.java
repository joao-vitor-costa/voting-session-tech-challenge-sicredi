package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.OpenVotingSessionInDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Sessions")
public interface SessionController {

    @Operation(summary = "open a voting session on top of an agenda")
    @ApiResponse(responseCode = "204", description = "No Content")
    void openVotingSession(OpenVotingSessionInDTO votingSessionInDTO);
}
