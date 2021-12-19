package ru.gb.mall.inventory.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.service.WarehouseService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses() {
        return ResponseEntity.ok(warehouseService.findAllWarehouses());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findWarehouseById(
            @PathVariable("id") long id,
            @RequestParam(required = false, defaultValue = "false") boolean showProducts
    ) {
        Object result;
        if (showProducts) {
            result = warehouseService.findWarehouseById(id);
        } else {
            result = warehouseService.findWarehouseById(id);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{warehouseId}/product/{productId}")
    public ResponseEntity<WarehouseItem> findWarehouseItemsById(
            @PathVariable() long warehouseId,
            @PathVariable() long productId
    ) {
        return ResponseEntity.ok(warehouseService.findWarehouseItemByWarehouseAndProductId(warehouseId, productId));
    }

    @PostMapping
    public ResponseEntity<?> createWarehouseAndWarehouseKeeper(@RequestBody Warehouse warehouse) {
        Warehouse newWarehouse = warehouseService.createWarehouseAndWarehouseKeeper(warehouse);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCategoryUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newWarehouse.getId())
                .toUri();
        responseHeaders.setLocation(newCategoryUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> writeOffProductFromWarehouse(
            @PathVariable("id") long warehouseId,
            @RequestBody WarehouseItem shipmentItem) {
        if (warehouseService.writeOffProductFromWarehouse(warehouseId, shipmentItem)) {
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}