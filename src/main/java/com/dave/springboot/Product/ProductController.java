package com.dave.springboot.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired // auto thêm ProductService vào đây
    ProductService service;

    @RequestMapping("/products")
    public List<Product> getProducts () {
        System.out.println("abc");
        return service.getProducts();
    }
}
