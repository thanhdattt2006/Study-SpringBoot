package com.dave.springboot.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired // auto thêm ProductService vào đây
    ProductService service;

    @RequestMapping()
    public List<Product> getProducts() {
        try {
            return service.getProducts();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}") // {id} là biển hướng dẫn
    public Product getProductById(@PathVariable int id) {  // @PathVariable hứng {id} trên URL vào biến id của function
        try {
            return service.getProductById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping() // Dùng PostMapping cho hành động Thêm mới
    public void addProduct(@RequestBody Product p) {   // @RequestBody: Hứng cục JSON từ body request, ép kiểu sang Object Product
        try {
            service.addProduct(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping() // PutMapping cho update
    public void updateProduct(@RequestBody Product p) {
        try {
            service.updateProduct(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        try {
            service.deleteProduct(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
