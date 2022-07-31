package com.joao.dataprovider.dto;

public record StandardError(Long timestamp, Integer status, String error, String message, String request, String path) {
}
