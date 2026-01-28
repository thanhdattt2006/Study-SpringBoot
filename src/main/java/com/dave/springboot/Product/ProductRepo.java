package com.dave.springboot.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    // không cần viết, tự có sẵn hàm findAll, save, delete...
}
