package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.ProductDiscount;
import ru.gb.mall.inventory.entity.ProductPrice;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.ProductDiscountRepository;
import ru.gb.mall.inventory.repository.ProductPriceRepository;
import ru.gb.mall.inventory.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;
    private final ProductDiscountRepository productDiscountRepository;

    public ProductService(ProductRepository productRepository, ProductPriceRepository productPriceRepository, ProductDiscountRepository productDiscountRepository) {
        this.productRepository = productRepository;
        this.productPriceRepository = productPriceRepository;
        this.productDiscountRepository = productDiscountRepository;
    }

    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), true).toList();
    }

    public Product findById(long id) {
        try {
            return productRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + id, e);
        }
    }

    public boolean deleteById(Long id) {
        try {
           productRepository.deleteById(id);
           return true;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + id, e);
        }
    }

    public void saveOrUpdate(Product product) {

        try {
            productRepository.save(product);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + product.getId(), e);
        }

    }

    public boolean saveOrUpdate(Long productId) {
        Product product = findById(productId);
        if (product == null) {
            return false;
        }
        saveOrUpdate(product);
        return true;
    }

    public boolean saveOrUpdatePrice(Long productId, ProductPrice productPrice){
        Product product = findById(productId);
        if (product == null) {
            return false;
        }
        product.setPrice(productPrice);
        updatePrice(product, productPrice);
        return true;

    }
    public void updatePrice(Product product, ProductPrice price) {

        try {
            productRepository.save(product);
            productPriceRepository.save(price);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + product.getId(), e);
        }

    }
    public boolean deleteByIdPrice (Long productId) {

        try {
            productPriceRepository.deleteById(findById(productId).getPrice().getId());
            return true;
        } catch (NoSuchElementException e) {

            throw new EntityNotFoundException("Price entity no found by id: " + productId, e);
        }
    }

    public boolean saveOrUpdateDiscount(Long productId, ProductDiscount discount){
        Product product = findById(productId);
        if (findById(productId) == null) {
            return false;
        }
        product.setDiscount(discount);
        updateDiscount(product, discount);
        return true;

    }
    public void updateDiscount(Product product, ProductDiscount discount) {

        try {
            productRepository.save(product);
            productDiscountRepository.save(discount);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + product.getId(), e);
        }

    }

    public boolean deleteByIdDiscount(Long productId) {
        try {
            productPriceRepository.deleteById(findById(productId).getDiscount().getId());
            return true;
        } catch (NoSuchElementException e) {

            throw new EntityNotFoundException("Discount entity no found by id: " + productId, e);
        }
    }
}