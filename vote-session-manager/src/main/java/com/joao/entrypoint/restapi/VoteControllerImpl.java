package com.joao.entrypoint.restapi;

import com.joao.core.usecase.VoteUseCase;
import com.joao.dataprovider.dto.VoteInDTO;
import com.joao.dataprovider.dto.VoteResultOutDTO;
import com.joao.dataprovider.mapper.VoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping ( "v1/votes" )
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

    private final VoteUseCase voteUseCase;
    private final VoteMapper voteMapper;

    @Override
    @PostMapping
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    public void vote(@RequestBody @Valid final VoteInDTO voteInDTO) {
        voteUseCase.voteOnTheAgenda(voteInDTO.associateId(), voteInDTO.agendaId(), voteInDTO.voteDecision());
    }

    @Override
    @GetMapping ( "total-votes/{agenda_id}/agenda-id" )
    @ResponseStatus ( HttpStatus.OK )
    public VoteResultOutDTO totalVotes(@PathVariable ( "agenda_id" ) @NotNull final Long agendaId) {
        return voteMapper.toDTO(voteUseCase.resultOfTheVoteOnTheAgenda(agendaId));
    }
}
