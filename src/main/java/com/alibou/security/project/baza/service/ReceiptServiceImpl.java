package com.alibou.security.project.baza.service;

import com.alibou.security.project.baza.model.Receipt;
import com.alibou.security.project.baza.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public List<Receipt> findAllUserReceipts(Integer userId) {
        return receiptRepository.findAllByUser_Id(userId);
    }


}
