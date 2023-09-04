package com.alibou.security.project.image.service;

import com.alibou.security.project.baza.model.ImageReceipt;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {

    String uploadImage(MultipartFile file) throws Exception;

    byte[] downloadImage(String fileName);

    ImageReceipt findByName(String filename);

}
