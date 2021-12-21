package ru.gb.mall.inventory.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.entity.WarehouseKeeper;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.mail.EmailServiceImpl;
import ru.gb.mall.inventory.mail.message.EmailMessage;
import ru.gb.mall.inventory.repository.ProductRepository;
import ru.gb.mall.inventory.repository.WarehouseItemRepository;
import ru.gb.mall.inventory.repository.WarehouseKeeperRepository;
import ru.gb.mall.inventory.repository.WarehouseRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseKeeperRepository keeperRepository;
    private final WarehouseItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final EmailServiceImpl emailService;

    public WarehouseService(WarehouseRepository warehouseRepository,
                            WarehouseKeeperRepository keeperRepository,
                            WarehouseItemRepository itemRepository,
                            ProductRepository productRepository,
                            EmailServiceImpl emailService) {
        this.warehouseRepository = warehouseRepository;
        this.keeperRepository = keeperRepository;
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }


    public List<Warehouse> findAllWarehouses() {
            return StreamSupport.stream(warehouseRepository.findAll().spliterator(), true).toList();
        }
        public Warehouse findWarehouseById(long id) {
            try {
                return warehouseRepository.findById(id).orElseThrow();
            }catch (NoSuchElementException e) {
                throw new EntityNotFoundException("Warehouse not found by id: " + id, e);
            }
        }
        public WarehouseItem findWarehouseItemByWarehouseAndProductId (long warehouseId, long productId) {
            Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(
                    () -> {
                        throw new EntityNotFoundException("Warehouse not found by id: " + warehouseId);
                    });
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> {
                        throw new EntityNotFoundException("Product not found by id: " + productId);
                    });
            try {
                return itemRepository.findByWarehouseAndProduct(warehouse, product).orElseThrow();
            } catch (NoSuchElementException e) {
                throw new EntityNotFoundException("Warehouse Item not found by warehouse Id: " + warehouseId +
                        " and product Id: " + productId, e);
            }
        }
        private void checkWarehouse(Warehouse warehouse) {
            if (warehouse.getAddress() == null) {
                throw new EntityNotFoundException("Warehouse address cannot be empty");
            }
            if (warehouse.getWarehouseKeeper() == null) {
                throw new EntityNotFoundException("Warehouse Keeper cannot be empty");
            }
        }
        @Transactional
        public Warehouse createWarehouseAndWarehouseKeeper(Warehouse warehouse) {
            checkWarehouse (warehouse);
            Optional<WarehouseKeeper> keeper = keeperRepository.findById(warehouse.getId());
            if (keeper.isPresent()) {
                throw new EntityNotFoundException(
                        "Warehouse Keeper already exists with name: " + warehouse.getWarehouseKeeper().getName());
            }
            Warehouse newWarehouse = new Warehouse();
            warehouseRepository.save(newWarehouse);
            WarehouseKeeper newKeeper = new WarehouseKeeper();
            newKeeper.setWarehouse(newWarehouse);
            keeperRepository.save(newKeeper);
            return newWarehouse;
        }
        public boolean writeOffProductFromWarehouse(Long warehouseId, WarehouseItem shipmentItem) {
            checkShipmentItem(shipmentItem);
            WarehouseItem item =itemRepository.findByWarehouseAndProduct(
                            warehouseRepository.getById(warehouseId),
                            shipmentItem.getProduct())
                    .orElseThrow ( () -> new EntityNotFoundException("Warehouse Item not found"));

            if(shipmentItem.getAmount()>=10) {
                sendingMessageWarehouseKeeper (warehouseRepository.getById(warehouseId).getWarehouseKeeper());
            } else  sendingMessageWarehouseKeeperLessThan10 (warehouseRepository.getById(warehouseId).getWarehouseKeeper());
            item.setAmount(item.getAmount() - shipmentItem.getAmount());
            itemRepository.saveAndFlush(item);
            return true;
        }
        private void  checkShipmentItem (WarehouseItem shipmentItem) {
            if (shipmentItem.getProduct() == null) {
                throw new EntityNotFoundException("Product Id cannot be empty");
            }
            if (shipmentItem.getAmount() <= 0) {
                throw new EntityNotFoundException("Product amount cannot be empty or negative");
            }
        }

        private void sendingMessageWarehouseKeeper (WarehouseKeeper keeper){
            EmailMessage message = new EmailMessage(
                    keeper.getEmail()
                    ,"Shipment of goods"
                    , "The product has been shipped"
            );
            emailService.send(message);
        }

        private void sendingMessageWarehouseKeeperLessThan10 (WarehouseKeeper keeper){
            EmailMessage message = new EmailMessage(
                    keeper.getEmail()
                    ,"Warning"
                    , "The number of shipped goods is less than 10"
            );
            emailService.send(message);
        }
    }