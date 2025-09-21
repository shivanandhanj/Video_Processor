package com.example.videoprocessor.service;

import com.example.videoprocessor.util.VideoUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class VideoService {

    private final String uploadDir = "uploads/";

    public String saveVideo(MultipartFile file) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String filePath = uploadDir + file.getOriginalFilename();
        File destination = new File(filePath);
        file.transferTo(destination);

        return filePath;
    }

    public String extractThumbnail(MultipartFile file) throws IOException {
        // First save the video
        String videoPath = saveVideo(file);

        // Extract thumbnail
        String thumbnailPath = uploadDir + "thumbnail_" + System.currentTimeMillis() + ".png";
        VideoUtils.extractFrame(videoPath, thumbnailPath);

        return thumbnailPath;
    }
}
