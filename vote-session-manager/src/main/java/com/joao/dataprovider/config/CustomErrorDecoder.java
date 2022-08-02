package com.joao.dataprovider.config;

import com.joao.core.exception.BadRequestException;
import com.joao.core.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

import static com.joao.core.enumeration.ExceptionCodeEnumeration.BAD_REQUEST;
import static com.joao.core.enumeration.ExceptionCodeEnumeration.NOT_FOUND;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException(BAD_REQUEST.message, BAD_REQUEST.code);
            case 404 -> new NotFoundException(NOT_FOUND.message, NOT_FOUND.code);
            default -> new Exception(response.reason());
        };
    }
}