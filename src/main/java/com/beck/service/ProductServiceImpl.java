package com.beck.service;

import com.beck.dao.ProductDao;
import com.beck.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void createProduct(Product product) {
        productDao.createProduct(product);

    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    @Transactional
    public void removeProduct(long id) {
        productDao.removeProduct(id);

    }

    @Override
    public Product getProductById(long id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> findProducts(String string) {
        return productDao.findProducts(string);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productDao.updateProduct(product);

    }

    @Override
    public void orderById(List<Product> productList) {
        productList.sort((Product p1,Product p2)-> (int) (p1.getId()- p2.getId()));

    }

    @Override
    public void orderByName(List<Product> productList) {
        productList.sort((Product p1,Product p2)->{
            if(p1.getName().compareTo(p2.getName()) !=0){
                return p1.getName().compareTo(p2.getName());
            }else {
                return (int) (p1.getId()- p2.getId());
            }
        });
    }

    @Override
    public void orderByPrice(List<Product> productList) {
        productList.sort((Product p1,Product p2)->{
            if ((p1.getPrice() - p2.getPrice()) !=0){
                return p1.getPrice() - p2.getPrice();
            }else {
                return (int) (p1.getId() - p2.getId());
            }
        });

    }

    @Override
    public void orderByType(List<Product> productList) {
        productList.sort((Product p1,Product p2)->{
            if (p1.getType().getName().compareTo(p2.getType().getName()) !=0){
                return p1.getType().getName().compareTo(p2.getType().getName());
            }else {
                return (int) (p1.getId() - p2.getId());
            }
        });
    }
}
