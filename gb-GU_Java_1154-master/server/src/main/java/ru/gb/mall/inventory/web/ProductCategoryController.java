package ru.gb.mall.inventory.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.entity.ProductCategory;
import ru.gb.mall.inventory.service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {

    private final ProductCategoryService categoryService;

    public ProductCategoryController(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<List<ProductCategory>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        final boolean deleted = categoryService.deleteById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.ACCEPTED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<?> saveOrUpdateProduct (@PathVariable("id") Long id) {
        final boolean saveOrUpdate = categoryService.saveOrUpdate(id);

        return saveOrUpdate
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
