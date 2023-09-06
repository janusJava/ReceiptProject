package com.alibou.security.project.image.controller;

import com.alibou.security.project.image.google.GoogleCloudService;
import com.alibou.security.project.image.service.ImageService;
import com.google.cloud.storage.BlobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image/")
public class ImageController {

    private final ImageService imageService;
    private final GoogleCloudService googleCloudService;

    @Autowired
    public ImageController(ImageService imageService, GoogleCloudService googleCloudService) {
        this.imageService = imageService;
        this.googleCloudService = googleCloudService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws Exception {
        System.out.println("gowno");
        //TODO: LOGIKE DO SERWISU!!!!!!!!!!!!!!!!!
        String uploadImage = imageService.uploadImage(file);
        BlobInfo blobInfo = googleCloudService.uploadObjectFromMemory(file.getBytes());
        googleCloudService.detectText(blobInfo.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
