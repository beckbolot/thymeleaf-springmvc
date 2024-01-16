package com.beck.dao;

import com.beck.entity.FinalOrder;
import com.beck.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;
    private static Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createProduct(Product product) {
        sessionFactory.getCurrentSession().persist(product);
        logger.info("Product was created: " + product);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAllProducts() {
        return sessionFactory.getCurrentSession().createQuery("from Product", Product.class).list();
    }

    @Override
    public void removeProduct(long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);

        NativeQuery<FinalOrder> nativeQuery = sessionFactory.getCurrentSession().
                createNativeQuery("delete from finalorder_products where products_id =:id", FinalOrder.class);
        nativeQuery.setParameter("id", product.getId());
        nativeQuery.executeUpdate();

        sessionFactory.getCurrentSession().remove(product);
        logger.info("Product was deleted: " + product);
    }

    @Override
    public Product getProductById(long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public List<Product> findProducts(String string) {
        String sql = "FROM Product WHERE name LIKE '"+ string + "%'";
        List<Product> products = sessionFactory.getCurrentSession().createQuery(sql, Product.class).list();

        if (products != null) {
            for (Product currentProduct : products) {
                logger.info("Product was found: " + currentProduct);
            }
        }

        return products;
    }

    @Override
    public void updateProduct(Product product) {
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.detach(product);
            currentSession.merge(product);
        }

        logger.info("Product was updated: " + product);

    }
}
