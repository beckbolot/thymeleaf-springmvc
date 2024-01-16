package com.beck.service;

import com.beck.entity.Product;
import com.beck.entity.User;

import java.util.List;

public interface BasketOrderService {

    boolean addProduct(long userId, long productId);

    void removeProduct(long userId, long productId);

    List<Product> getProductsByUserId(long userId);

    boolean doOrder(User user);
}
