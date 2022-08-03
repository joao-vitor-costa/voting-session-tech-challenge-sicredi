package com.joao.entrypoint.restapi;

import com.joao.core.usecase.SessionUseCase;
import com.joao.dataprovider.dto.OpenVotingSessionInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("/v1/sessions")
@RequiredArgsConstructor
public class SessionControllerImpl implements SessionController {

    private final SessionUseCase sessionUseCase;

    @Override
    @PostMapping
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void openVotingSession(@RequestBody @Valid final OpenVotingSessionInDTO openVotingSessionInDTO) {
        sessionUseCase.openAVotingSessionOnAnAgenda(openVotingSessionInDTO.agendaId(), openVotingSessionInDTO.sessionTime());
    }
}
