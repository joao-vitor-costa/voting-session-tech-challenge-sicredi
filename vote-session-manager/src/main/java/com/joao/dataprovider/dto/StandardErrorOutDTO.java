package com.joao.dataprovider.dto;

public record StandardErrorOutDTO(Long timestamp, Integer status, String error, String message, String request, String path) {
}
