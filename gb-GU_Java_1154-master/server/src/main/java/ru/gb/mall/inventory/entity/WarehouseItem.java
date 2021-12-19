package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@IdClass(WarehouseItemId.class)
@Table(name = "WarehouseItems")
@Data
public class WarehouseItem {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID", referencedColumnName = "ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_WAREHOUSEITEM_WAREHOUSE_ID_RELATION")
    )
    private Warehouse warehouse;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "FK_WAREHOUSEITEM_PRODUCT_ID_RELATION",
                    foreignKeyDefinition = "FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS(ID) ON DELETE CASCADE ON UPDATE NO ACTION"
            )
    )
    private Product product;

    @Column(name = "AMOUNT")
    private Integer amount;
}