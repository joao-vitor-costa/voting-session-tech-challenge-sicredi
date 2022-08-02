package com.joao.entrypoint.config;

import com.joao.core.exception.*;
import com.joao.dataprovider.dto.StandardErrorOutDTO;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler ( NotFoundException.class )
    public ResponseEntity<StandardErrorOutDTO> notFound(NotFoundException exception, HttpServletRequest request) {
        final var standardError = new StandardErrorOutDTO(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                exception.getErrorCode(), exception.getMessage(), request.getRequestURI(), request.getMethod());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler ( PropertyValueException.class )
    public ResponseEntity<StandardErrorOutDTO> propertyValue(PropertyValueException exception, HttpServletRequest request) {
        final var standardError = new StandardErrorOutDTO(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                null, exception.getMessage(), request.getRequestURI(), request.getMethod());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }


    @ExceptionHandler ( SessionNotCreatedException.class )
    public ResponseEntity<StandardErrorOutDTO> sessionNotCreated(SessionNotCreatedException exception, HttpServletRequest request) {
        final var standardError = new StandardErrorOutDTO(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                exception.getErrorCode(), exception.getMessage(), request.getRequestURI(), request.getMethod());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(standardError);
    }

    @ExceptionHandler ( CloseSessionException.class )
    public ResponseEntity<StandardErrorOutDTO> closeSession(CloseSessionException exception, HttpServletRequest request) {
        final var standardError = new StandardErrorOutDTO(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                exception.getErrorCode(), exception.getMessage(), request.getRequestURI(), request.getMethod());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(standardError);
    }

    @ExceptionHandler ( NotEligibleVoteException.class )
    public ResponseEntity<StandardErrorOutDTO> NotEligibleVote(NotEligibleVoteException exception, HttpServletRequest request) {
        final var standardError = new StandardErrorOutDTO(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                exception.getErrorCode(), exception.getMessage(), request.getRequestURI(), request.getMethod());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(standardError);
    }

    @ExceptionHandler ( SecondVoteAttemptException.class )
    public ResponseEntity<StandardErrorOutDTO> SecondVoteAttempt(SecondVoteAttemptException exception, HttpServletRequest request) {
        final var standardError = new StandardErrorOutDTO(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                exception.getErrorCode(), exception.getMessage(), request.getRequestURI(), request.getMethod());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(standardError);
    }


}
