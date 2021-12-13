package ru.gb.mall.inventory.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.ProductDiscount;
import ru.gb.mall.inventory.entity.ProductPrice;
import ru.gb.mall.inventory.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id){

            return productService.deleteById(id)
                    ? new ResponseEntity<>(HttpStatus.ACCEPTED)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    @PostMapping("/save/{id}")
    public ResponseEntity<?> saveOrUpdateProduct (@PathVariable("id") Long id) {

        return productService.saveOrUpdate(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/updatePrice/{id}")
    public ResponseEntity<?> saveOrUpdatePrice(@PathVariable("id") Long id, @RequestBody ProductPrice price){
        return productService.saveOrUpdatePrice(id, price)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/deleteByIdPrice/{id}")
    public ResponseEntity<?> deleteByIdPrice(@PathVariable("id") Long productId){

        return productService.deleteByIdPrice(productId)
                ? new ResponseEntity<>(HttpStatus.ACCEPTED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/updateDiscount/{id}")
    public ResponseEntity<?> saveOrUpdateDiscount (@PathVariable("id") Long id, @RequestBody ProductDiscount discount){
        return  productService.saveOrUpdateDiscount(id, discount)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
