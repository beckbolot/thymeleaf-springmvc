package com.beck.service;

import com.beck.entity.Product;

import java.util.List;

public interface ProductService {

    void createProduct(Product product);

    List<Product> getAllProducts();

    void removeProduct(long id);

    Product getProductById(long id);

    List<Product> findProducts(String string);

    void updateProduct(Product product);

    void orderById(List<Product> productList);

    void orderByName(List<Product> productList);

    void orderByPrice(List<Product> productList);

    void orderByType(List<Product> productList);
}
