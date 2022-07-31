package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.in.VotingSessionInDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Sessions")
public interface SessionController {

    @Operation(summary = "open a voting session on top of an agenda")
    void openVotingSession(VotingSessionInDTO votingSessionInDTO);
}
