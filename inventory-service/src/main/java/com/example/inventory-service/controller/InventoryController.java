package com.example.inventoryservice.controller;

import com.example.inventoryservice.entity.InventoryItem;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{warehouseName}")
    public InventoryItem addItem(@RequestBody InventoryItem item, @PathVariable String warehouseName) {
        return inventoryService.addItem(item, warehouseName);
    }

    @GetMapping("/{warehouseName}")
    public List<InventoryItem> getItems(@PathVariable String warehouseName) {
        return inventoryService.getItemsByWarehouse(warehouseName);
    }

    @GetMapping("/item/{itemId}")
    public InventoryItem getItemById(@PathVariable Long itemId) {
        return inventoryService.getItemById(itemId);
    }

    @GetMapping
    public List<InventoryItem> getAllItems() {
        return inventoryService.getAllItems();
    }

    @PutMapping("/item/{itemId}")
    public InventoryItem updateItem(@PathVariable Long itemId, @RequestBody InventoryItem item) {
        return inventoryService.updateItem(itemId, item);
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        inventoryService.removeItem(itemId);
    }
}
