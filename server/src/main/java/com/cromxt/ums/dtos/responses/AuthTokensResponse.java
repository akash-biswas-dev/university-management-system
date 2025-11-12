package com.cromxt.ums.dtos.responses;

public record AuthTokensResponse(
    String accessToken,
    String refreshToken
) {

}
