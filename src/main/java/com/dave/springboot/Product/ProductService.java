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

    public List<Product> getProducts () {
        return products;
    }
}
