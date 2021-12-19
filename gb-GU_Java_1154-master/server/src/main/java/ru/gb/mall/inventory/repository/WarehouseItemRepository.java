package ru.gb.mall.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.entity.WarehouseItemId;

import java.util.Optional;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, WarehouseItemId> {
   Optional<WarehouseItem> findByWarehouseAndProduct(Warehouse warehouse, Product product);
}
