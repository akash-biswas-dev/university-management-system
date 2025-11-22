package com.cromxt.ums.dtos.responses;

import com.cromxt.ums.models.UserStatus;

import java.time.LocalDate;

public record FacultyResponse (
        String firstName,
        String lastName,
        String username,
        LocalDate joinedOn,
        String imageId,
        UserStatus status
){
}
