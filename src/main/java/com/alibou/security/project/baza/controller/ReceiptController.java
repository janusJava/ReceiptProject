package com.alibou.security.project.baza.controller;

import com.alibou.security.project.baza.model.Receipt;
import com.alibou.security.project.baza.service.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/receipt")
@Slf4j
public class ReceiptController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService){
        this.receiptService=receiptService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Receipt>> findAllReceipts(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(receiptService.findAllReceipts());
    }

    @DeleteMapping(value = "/{receiptId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReceipt(@PathVariable Integer receiptId) {
        log.info("Meter controller: Deleting meter {id: {}}", receiptId);
        receiptService.deleteReceipt(receiptId);
        log.debug("Meter controller: Deleted meter {id: {}}", receiptId);
    }

    @GetMapping("/sum")
    public ResponseEntity<BigDecimal>sumOfAllReceipts(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(receiptService.sumOfAllReceipts());
    }
}
