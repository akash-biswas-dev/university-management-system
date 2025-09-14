package com.cromxt.ums.dtos.responses;

public record AuthTokensDTO(
    String accessToken,
    String refreshToken) {

}
