package com.cromxt.ums.dtos.requests;


import org.springframework.web.multipart.MultipartFile;

public record RegisterWorkspace(
        String workspaceName,
        String description,
        MultipartFile profileImage,
        RegisterAddressDTO address
) {
}
