package com.joao.entrypoint.restapi;

import com.joao.dataprovider.dto.in.OpenVotingSessionInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("v1/sessions")
public class SessionControllerImpl implements SessionController {

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void openVotingSession(@RequestBody @Valid final OpenVotingSessionInDTO openVotingSessionInDTO) {

    }
}
