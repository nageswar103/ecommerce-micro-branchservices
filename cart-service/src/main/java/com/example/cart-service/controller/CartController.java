package com.example.cartservice.controller;

import com.example.cartservice.dto.CartItemRequest;
import com.example.cartservice.entity.CartItem;
import com.example.cartservice.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public CartItem addItem(@RequestBody CartItemRequest request) {
        return cartService.addItem(request);
    }

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartService.getAllItems();
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return cartService.getUserCart(userId);
    }

    @PutMapping("/{itemId}")
    public CartItem updateItem(@PathVariable Long itemId, @RequestBody CartItemRequest request) {
        return cartService.updateItem(itemId, request);
    }

    @DeleteMapping("/{itemId}")
    public void removeItem(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
    }
}
