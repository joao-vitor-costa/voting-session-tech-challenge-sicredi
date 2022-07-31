package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.in.VotingSessionInDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/sessions")
public class SessionControllerImpl implements SessionController {

    @Override
    @PostMapping
    public void openVotingSession(@RequestBody @Valid final VotingSessionInDTO votingSessionInDTO) {

    }
}
