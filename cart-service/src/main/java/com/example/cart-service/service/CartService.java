package com.example.cartservice.service;

import com.example.cartservice.dto.CartItemRequest;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartItem;
import com.example.cartservice.repository.CartItemRepository;
import com.example.cartservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public CartService(CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    public CartItem addItem(CartItemRequest request) {
        Optional<Cart> existingCart = cartRepository.findByUserId(request.getUserId());
        Cart cart = existingCart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(request.getUserId());
            return cartRepository.save(newCart);
        });

        CartItem item = new CartItem();
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());
        item.setCart(cart);

        return cartItemRepository.save(item);
    }

    public List<CartItem> getUserCart(Long userId) {
        return cartItemRepository.findByCartUserId(userId);
    }

    public List<CartItem> getAllItems() {
        return cartItemRepository.findAll();
    }

    public void removeItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public CartItem updateItem(Long itemId, CartItemRequest request) {
        return cartItemRepository.findById(itemId).map(item -> {
            item.setQuantity(request.getQuantity());
            item.setProductId(request.getProductId()); // Optional: update product too
            return cartItemRepository.save(item);
        }).orElse(null);
    }
}
