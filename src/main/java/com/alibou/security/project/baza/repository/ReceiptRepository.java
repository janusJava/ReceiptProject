package com.alibou.security.project.baza.repository;


import com.alibou.security.project.baza.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    List<Receipt> findAllByUser_Id(Integer userId);
}
