package com.example.Video_Processor.service;

import com.example.Video_Processor.util.VideoUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VideoService {

    
    @Value("${app.upload.dir}")
    private String uploadDir;

    public String saveVideo(MultipartFile file) throws IOException {
        Path dirPath = Paths.get(uploadDir);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        Path filePath = dirPath.resolve(file.getOriginalFilename());
        file.transferTo(filePath.toFile());

        return filePath.toString();
    }

    public String extractThumbnail(MultipartFile file) throws IOException {
        String videoPath = saveVideo(file);

        String thumbnailPath = uploadDir + "/thumbnail_" + System.currentTimeMillis() + ".png";
        VideoUtils.extractFrame(videoPath, thumbnailPath);

        return thumbnailPath;
    }
}
