package com.alibou.security.project.baza.service;

import com.alibou.security.project.baza.model.Receipt;
import com.alibou.security.project.baza.repository.ReceiptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@Slf4j
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

    @Override
    public List<Receipt> findAllReceipts(){
        return receiptRepository.findAll();
    }

    @Override
    public BigDecimal sumOfAllReceipts() {
        List<Receipt> receiptList = findAllReceipts();
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Receipt receipt : receiptList) {
            BigDecimal receiptAmount = receipt.getMoney();
            if (receiptAmount != null) {
                totalSum = totalSum.add(receiptAmount);
            }
        }
        return totalSum;
    }

    @Override
    public void deleteReceipt(Integer receiptId) {
        log.info("Service: Deleting meter");
        receiptRepository.deleteById(receiptId);
        log.debug("Service: Deleted meter");
    }

}
