package com.joao.entrypoint.config;

import com.joao.core.exception.NotFoundException;
import com.joao.dataprovider.dto.StandardErrorOutDTO;
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
}
