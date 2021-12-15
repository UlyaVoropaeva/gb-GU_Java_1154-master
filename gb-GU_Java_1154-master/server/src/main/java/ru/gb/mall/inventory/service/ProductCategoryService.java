package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.ProductCategory;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.ProductCategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;

    public ProductCategoryService(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ProductCategory> findAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), true).toList();
    }

    public ProductCategory findById(long id) {
        try {
            return categoryRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("category entity no found by id: " + id, e);
        }
    }

    public boolean deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("category entity no found by id: " + id, e);
        }
    }

    public void saveOrUpdate(ProductCategory category) {

        try {
            categoryRepository.save(category);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("category entity no found by id: " + category.getId(), e);
        }
    }

    public boolean saveOrUpdate(Long categoryId) {
        ProductCategory category = findById(categoryId);
        if (category == null) {
            return false;
        }
        saveOrUpdate(category);
        return true;
    }
}
