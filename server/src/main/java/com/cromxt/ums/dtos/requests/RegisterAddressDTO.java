package com.cromxt.ums.dtos.requests;

public record RegisterAddressDTO(
        String country,
        String state,
        String street,
        Integer postalCode
) {
}
