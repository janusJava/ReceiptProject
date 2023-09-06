package com.alibou.security.project.baza.model;

import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer receiptId;

    @Column()
    LocalDate date;

    @Column()
    String shopName;

    @Column()
    BigDecimal money;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne
    @JoinColumn(name = "imageReceipt_id")
    ImageReceipt imageReceipt;

    @OneToMany(mappedBy = "receipt")
    List<Product> productList;

}
