package com.cromxt.ums.services.impl;


import com.cromxt.ums.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
@Profile(value = "local-file")
public class LocalFileServiceImpl implements FileService {


    private final String filePath;

    public LocalFileServiceImpl(Environment environment) {
        filePath = environment.getProperty("local-file.path", String.class);

        File file = new File("classpath:ums-data");
        if (!file.exists()) {
            if (file.mkdir()) {
                log.info("Created local directory");
            } else {
                log.error("Failed to create local directory");
                throw new RuntimeException("Failed to create local directory");
            }
        } else {
            log.info("Local directory already exists");
        }
    }

    @Override
    public FileDetails uploadFile(MultipartFile file) throws IOException {
        String fileId = UUID.randomUUID().toString();
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileName = String.format("%s-%s", fileId, extension);
        Files.copy(file.getInputStream(),Path.of(filePath, fileName), StandardCopyOption.REPLACE_EXISTING);
        return new FileDetails(fileId,extension);
    }

    @Override
    public byte[] getFileBytesById(String id) {
        return null;
    }


}
