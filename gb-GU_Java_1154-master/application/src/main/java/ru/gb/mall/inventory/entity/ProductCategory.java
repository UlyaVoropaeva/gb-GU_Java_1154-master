package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "PRODUCT_CATEGORIES")
@Entity
@Data
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false, unique = true, length = 50)
    private String name;

}
