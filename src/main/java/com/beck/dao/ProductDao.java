package com.beck.dao;

import com.beck.entity.Product;

import java.util.List;

public interface ProductDao {

    void createProduct(Product product);

    List<Product> getAllProducts();

    void removeProduct(long id);

    Product getProductById(long id);

    List<Product> findProducts(String string);

    void updateProduct(Product product);
}
