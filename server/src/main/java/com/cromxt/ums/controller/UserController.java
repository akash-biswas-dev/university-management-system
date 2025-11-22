package com.cromxt.ums.controller;


import com.cromxt.ums.dtos.requests.UserProfileRequest;
import com.cromxt.ums.dtos.responses.UserProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserProfileResponse> updateProfile(
            @RequestBody UserProfileRequest userProfileRequest) {
        return null;
    }
}
