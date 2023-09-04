package com.alibou.security.project.baza.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int quantity;

    @Column(nullable = false)
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    Receipt receipt;

}
