package com.beck.dao;

import com.beck.entity.BasketOrder;
import com.beck.entity.FinalOrder;
import com.beck.entity.Product;
import com.beck.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BasketOrderDaoImpl implements BasketOrderDao {


    private SessionFactory sessionFactory;

    @Autowired
    public BasketOrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static Logger logger = LoggerFactory.getLogger(BasketOrderDaoImpl.class);


    @Override
    @SuppressWarnings("unchecked")
    public boolean addProduct(long userId, long productId) {
        User user = sessionFactory.getCurrentSession().get(User.class, userId);
        Product product = sessionFactory.getCurrentSession().get(Product.class, productId);

        Query<BasketOrder> query = sessionFactory.getCurrentSession()
                .createQuery("from BasketOrder where user =:user and product=:product", BasketOrder.class);
        query.setParameter("user", user);
        query.setParameter("product", product);

        if (query.getResultList().isEmpty()) {
            sessionFactory.getCurrentSession().persist(new BasketOrder(user, product));
            logger.info("BasketOrder was added: userId(" + userId + "), product_id(" + productId + ")");
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeProduct(long userId, long productId) {
        User user = sessionFactory.getCurrentSession().get(User.class, userId);
        Product product = sessionFactory.getCurrentSession().get(Product.class, productId);

        Query<BasketOrder> query = sessionFactory.getCurrentSession()
                .createQuery("from BasketOrder where user=:user and product=:product", BasketOrder.class);
        query.setParameter("user", user);
        query.setParameter("product", product);
        List<BasketOrder> resultList = query.getResultList();
        sessionFactory.getCurrentSession().remove(resultList.get(0));

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getProductsByUserId(long userId) {
        User user = sessionFactory.getCurrentSession().get(User.class, userId);
        Query<BasketOrder> query = sessionFactory.getCurrentSession().createQuery("from BasketOrder where user =:user", BasketOrder.class);
        query.setParameter("user", user);

        List<BasketOrder> resultList = query.getResultList();
        List<Product> products = new ArrayList<>();

        for (BasketOrder currentObject : resultList) {
            products.add(currentObject.getProduct());
        }

        return products;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean doOrder(User user) {
        Query<FinalOrder> checkOrder = sessionFactory.getCurrentSession()
                .createQuery("from FinalOrder where user =:user", FinalOrder.class).setParameter("user", user);

        if (!checkOrder.getResultList().isEmpty()){
            return false;
        }

        Query<BasketOrder> query = sessionFactory.getCurrentSession()
                .createQuery("from BasketOrder where user=:user", BasketOrder.class).setParameter("user", user);
        List<BasketOrder> resultList = query.getResultList();
        Date date = new Date();

        List<Product> products = new ArrayList<>();

        for (BasketOrder currentObject: resultList){
            products.add(currentObject.getProduct());
        }

        sessionFactory.getCurrentSession().persist(new FinalOrder(date,user,products));
        sessionFactory.getCurrentSession().createQuery("delete from BasketOrder where user=:user", BasketOrder.class).setParameter("user",user).executeUpdate();

        return true;
    }
}
