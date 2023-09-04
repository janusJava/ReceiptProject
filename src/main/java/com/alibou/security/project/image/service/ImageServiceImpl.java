package com.alibou.security.project.image.service;

import com.alibou.security.project.baza.model.ImageReceipt;
import com.alibou.security.project.exception.CouldNotFindEntityException;
import com.alibou.security.project.image.repository.ImageReceiptRepository;
import com.alibou.security.project.image.utils.ImageSize;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageReceiptRepository imageReceiptRepository;

    @Autowired
    public ImageServiceImpl(ImageReceiptRepository imageReceiptRepository) {
        this.imageReceiptRepository = imageReceiptRepository;
    }

    @Override
    @Transactional
    public String uploadImage(MultipartFile file) throws Exception {
        ImageReceipt imageData = ImageReceipt.builder().name(file.getOriginalFilename()).type(file.getContentType()).imageData(ImageSize.compressImage(file.getBytes())).build();
        imageReceiptRepository.save(imageData);
        log.info("File uploaded successfully : " + file.getOriginalFilename());
        return "Success";
    }

    @Override
    public byte[] downloadImage(String fileName) {
        ImageReceipt imageReceipt = findByName(fileName);
        return ImageSize.decompressImage(imageReceipt.getImageData());
    }

    @Override
    public ImageReceipt findByName(String filename) {
        Optional<ImageReceipt> imageReceipt = imageReceiptRepository.findByName(filename);
        if (imageReceipt.isPresent()) {
            return imageReceipt.get();
        } else {
            throw new CouldNotFindEntityException("Could not find ImageReceipt entity with provided filename: " + filename);
        }
    }
}
