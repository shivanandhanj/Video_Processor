package com.example.videoprocessor.controller;

import com.example.videoprocessor.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            String path = videoService.saveVideo(file);
            return ResponseEntity.ok("Video uploaded successfully: " + path);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error uploading video: " + e.getMessage());
        }
    }

    @PostMapping("/thumbnail")
    public ResponseEntity<String> extractThumbnail(@RequestParam("file") MultipartFile file) {
        try {
            String thumbnailPath = videoService.extractThumbnail(file);
            return ResponseEntity.ok("Thumbnail saved at: " + thumbnailPath);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error extracting thumbnail: " + e.getMessage());
        }
    }
}
