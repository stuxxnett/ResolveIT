package com.resolveit.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUploadUtil {

    private static final String UPLOAD_DIR = "uploads/complaints/";

    public static String saveFile(MultipartFile file) throws IOException {

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs(); // ✅ REQUIRED
        }

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + filename);

        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return path.toString();
    }
}
