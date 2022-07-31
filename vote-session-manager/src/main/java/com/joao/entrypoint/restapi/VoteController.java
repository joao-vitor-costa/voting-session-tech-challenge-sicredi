package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.in.VoteInDTO;
import com.joao.dataprovider.dto.out.VoteResultOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Tag(name = "Votes")
public interface VoteController {

    @Operation(summary = "allow a member to vote on an open-session agenda")
    void vote(VoteInDTO voteInDTO);

    @Operation(summary = "result of the vote of a session on an agenda")
    @Parameter(name = "AgendaId", in = PATH, required = true, description = "unique agenda identifier", example = "25648486468")
    @Parameter(name = "SessionId", in = PATH, required = true, description = "unique session identifier", example = "25648486468")
    VoteResultOutDTO totalVotes(Long SessionId, Long AgendaId);


}
