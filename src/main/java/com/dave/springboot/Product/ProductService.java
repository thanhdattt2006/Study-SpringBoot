package com.dave.springboot.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    // Lấy hết: SELECT * FROM Product
    public List<Product> getProducts() {
        return repo.findAll();
    }

    // Lấy theo ID: SELECT * FROM Product WHERE id = ?
    public Product getProductById(int id) {
        return repo.findById(id).orElse(new Product());
    }

    // Thêm hoặc Sửa: INSERT hoặc UPDATE
    public void addProduct(Product prod) {
        repo.save(prod); // Hàm save() cân cả thêm mới lẫn update
    }

    public void updateProduct(Product prod) {
        repo.save(prod); // JPA thông minh: thấy có ID rồi thì nó tự Update
    }

    // Xóa: DELETE FROM Product WHERE id = ?
    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
}
