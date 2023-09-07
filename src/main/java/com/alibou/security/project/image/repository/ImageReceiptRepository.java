package com.alibou.security.project.image.repository;


import com.alibou.security.project.baza.model.ImageReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageReceiptRepository extends JpaRepository<ImageReceipt, Integer > {

    Optional<ImageReceipt> findByName(String fileName);
    Optional<ImageReceipt> findById(Integer id);

}
