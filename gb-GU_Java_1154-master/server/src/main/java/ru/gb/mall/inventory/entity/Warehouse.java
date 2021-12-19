package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Warehouse")
@Data
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @OneToOne(mappedBy = "warehouse")
    private WarehouseKeeper warehouseKeeper;

    @OneToMany(mappedBy = "warehouse", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<WarehouseItem> items = new ArrayList<>();
}