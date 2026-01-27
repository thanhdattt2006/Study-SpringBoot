package com.dave.springboot.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired // auto thêm ProductService vào đây
    ProductService service;

    @RequestMapping("/products")
    public List<Product> getProducts () {
        return service.getProducts();
    }

    @GetMapping("/products/{id}") // {id} là biển hướng dẫn
    public Product getProductById (@PathVariable int id) {  // @PathVariable hứng {id} trên URL vào biến id của function
        return service.getProductById(id);
    }

    @PostMapping("/products") // Dùng PostMapping cho hành động Thêm mới
    public void addProduct (@RequestBody Product p) {   // @RequestBody: Hứng cục JSON từ body request, ép kiểu sang Object Product
        service.addProduct(p);
    }

    @PutMapping("/products") // PutMapping cho update
    public void updateProduct (@RequestBody Product p) {
        service.updateProduct(p);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct (@PathVariable int id) {
        service.deleteProduct(id);
    }
}
