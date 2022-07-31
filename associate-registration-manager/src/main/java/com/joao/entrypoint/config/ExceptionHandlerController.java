package com.joao.entrypoint.config;

import com.joao.core.exception.NotFoundException;
import com.joao.dataprovider.dto.StandardError;
import com.mongodb.MongoWriteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<StandardError> mongoWrite(MongoWriteException exception, HttpServletRequest request) {
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Integer.toString(exception.getError().getCode()), exception.getMessage(), request.getRequestURI(), request.getMethod());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException exception, HttpServletRequest request) {
        final var standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                exception.getErrorCode(), exception.getMessage(), request.getRequestURI(), request.getMethod());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }
}
