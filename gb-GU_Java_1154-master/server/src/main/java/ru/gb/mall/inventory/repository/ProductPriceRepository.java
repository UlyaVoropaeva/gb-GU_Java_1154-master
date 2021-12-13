package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.mall.inventory.entity.ProductPrice;

public interface ProductPriceRepository extends PagingAndSortingRepository<ProductPrice, Long> {
}
