package com.alibou.security.project.baza.service;

import com.alibou.security.project.baza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
