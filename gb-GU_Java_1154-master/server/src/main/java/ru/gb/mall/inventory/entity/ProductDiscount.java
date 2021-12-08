package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "PRODUCT_DISCOUNT")
@Entity
@Data
public class ProductDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_idGenerator")
    @SequenceGenerator(name = "seq_idGenerator", sequenceName = "seq_productDiscountId", allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "ORIGINAL_VALUE", nullable = false)
    private double originalValue;
}

