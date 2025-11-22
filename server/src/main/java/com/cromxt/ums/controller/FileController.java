package com.cromxt.ums.controller;


import com.cromxt.ums.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;


    @GetMapping(value = "/api/v1/files/{fileId}")
    public byte[] getFileBytes(@PathVariable String fileId) {
        return fileService.getFileBytesById(fileId);
    }
}
