package com.cromxt.ums.controller;


import com.cromxt.ums.dtos.requests.FacultyRequest;
import com.cromxt.ums.dtos.responses.FacultyResponse;
import com.cromxt.ums.models.Permissions;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/faculties")
public class FacultyController {


    @PostMapping
    @PreAuthorize(value = "hasAnyAuthority('faculty:read')")
    public ResponseEntity<FacultyResponse> createFaculty(@RequestBody FacultyRequest facultyRequest){
        return null;
    }
}
