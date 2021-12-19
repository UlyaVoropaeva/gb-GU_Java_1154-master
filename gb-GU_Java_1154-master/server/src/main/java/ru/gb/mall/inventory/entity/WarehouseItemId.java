package ru.gb.mall.inventory.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WarehouseItemId implements Serializable {
    private Long warehouse;
    private Long product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseItemId war = (WarehouseItemId) o;
        return Objects.equals(warehouse, war.warehouse) && Objects.equals(product, war.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouse, product);
    }
}