package com.alibou.security.project.baza.service;

import com.alibou.security.project.baza.model.Receipt;

import java.util.List;

public interface ReceiptService {

    List<Receipt> findAllUserReceipts(Integer userId);

}
