package com.dave.springboot.Product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    List<Product> products = new ArrayList<>(Arrays.asList(new Product(1, "laptop", 689.5), new Product(2, "cpu", 199.5), new Product(3, "gpu", 439.5), new Product(4, "phone", 249.5), new Product(5, "screen", 120.5)));

    // all products
    public List<Product> getProducts() {
        try {
            return products;
        } catch (Exception e) {
            System.out.println("failed! error: " + e);
        }
        return List.of();
    }

    // get product by id
    public Product getProductById(int id) {
        try {
            return products.stream().filter(p -> p.getId() == id) // lọc product trùng id
                    .findFirst() // tìm cái đầu tiên
                    .orElse(new Product(100, "No item", 0)); // hong có return item rác
        } catch (Exception e) {
            System.out.println("failed! error: " + e);
        }
        return null;
    }

    // add product
    public void addProduct(Product p) {
        try {
            products.add(p);
        } catch (Exception e) {
            System.out.println("failed! error: " + e);
        }
    }

    // update product
    public void updateProduct(Product p) {
        try {
            for (int i = 0; i < products.size(); i++) {
                Product pro = products.get(i);
                if (p.getId() == pro.getId()) { // Tìm ID trùng
                    products.set(i, p); // Thay thế data cũ bằng data mới
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("failed! error: " + e);
        }
    }

    // delete product
    public void deleteProduct(int id) {
        try {
            for (int i = 0; i < products.size(); i++) {
                Product pro = products.get(i);
                if (id == pro.getId()) {
                    products.remove(i); // xoá khỏi list
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("failed! error: " + e);
        }
    }
}
