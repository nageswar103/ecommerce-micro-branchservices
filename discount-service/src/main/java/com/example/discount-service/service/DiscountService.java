package com.example.discountservice.service;

import com.example.discountservice.entity.Discount;
import com.example.discountservice.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public List<Discount> getActiveDiscounts() {
        LocalDate today = LocalDate.now();
        return discountRepository.findAll().stream()
            .filter(d -> d.getStartDate() != null && d.getEndDate() != null)
            .filter(d -> !today.isBefore(d.getStartDate()) && !today.isAfter(d.getEndDate()))
            .toList();
    }

    public Discount getDiscountById(Long id) {
        return discountRepository.findById(id).orElse(null);
    }

    public Discount updateDiscount(Long id, Discount updatedDiscount) {
        return discountRepository.findById(id).map(existing -> {
            existing.setCode(updatedDiscount.getCode());
            existing.setPercentage(updatedDiscount.getPercentage());
            existing.setActive(updatedDiscount.isActive());
            existing.setStartDate(updatedDiscount.getStartDate());
            existing.setEndDate(updatedDiscount.getEndDate());
            return discountRepository.save(existing);
        }).orElse(null);
    }

    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}
