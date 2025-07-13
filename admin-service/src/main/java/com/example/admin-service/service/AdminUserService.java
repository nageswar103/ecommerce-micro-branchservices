package com.example.adminservice.service;

import com.example.adminservice.entity.AdminUser;
import com.example.adminservice.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository repository;

    public AdminUser createAdminUser(AdminUser user) {
        user.setCreatedAt(LocalDateTime.now());
        return repository.save(user);
    }

    public List<AdminUser> getAllAdminUsers() {
        return repository.findAll();
    }

    public AdminUser getAdminUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public AdminUser updateAdminUser(Long id, AdminUser updatedUser) {
        return repository.findById(id).map(existing -> {
            existing.setUsername(updatedUser.getUsername());
            existing.setPassword(updatedUser.getPassword());
            return repository.save(existing);
        }).orElse(null);
    }

    public void deleteAdminUser(Long id) {
        repository.deleteById(id);
    }
}
