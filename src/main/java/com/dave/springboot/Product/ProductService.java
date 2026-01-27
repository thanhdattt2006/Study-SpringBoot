package com.dave.springboot.Product;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    List<Product> products = Arrays.asList(
            new Product(1, "laptop", 689.5),
            new Product(2, "cpu", 199.5),
            new Product(3, "gpu", 439.5),
            new Product(4, "phone", 249.5),
            new Product(5, "screen", 120.5)
    );

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id) // lọc product trùng id
                .findFirst() // tìm cái đầu tiên
                .orElse(new Product(100, "No item", 0)); // hong có return item rác
    }
}
