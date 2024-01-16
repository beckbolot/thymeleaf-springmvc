package com.beck.service;

import com.beck.dao.BasketOrderDao;
import com.beck.entity.Product;
import com.beck.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BasketOrderServiceImpl implements BasketOrderService{

    private final BasketOrderDao basketOrderDao;

    @Autowired
    public BasketOrderServiceImpl(BasketOrderDao basketOrderDao) {
        this.basketOrderDao = basketOrderDao;
    }

    @Transactional
    @Override
    public boolean addProduct(long userId, long productId) {
        return basketOrderDao.addProduct(userId,productId);
    }

    @Override
    @Transactional
    public void removeProduct(long userId, long productId) {
        basketOrderDao.removeProduct(userId, productId);

    }

    @Override
    public List<Product> getProductsByUserId(long userId) {
        return basketOrderDao.getProductsByUserId(userId);
    }

    @Override
    @Transactional
    public boolean doOrder(User user) {
        return basketOrderDao.doOrder(user);
    }
}
