package com.cromxt.ums.services;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {

    @AllArgsConstructor
    class FileDetails {
        String fileId;
        String extension;
        String getExtension() {
            return String.format("%s.%s", fileId, extension);
        }
    }

    FileDetails uploadFile(MultipartFile file) throws IOException;

    byte[] getFileBytesById(String id);

}
