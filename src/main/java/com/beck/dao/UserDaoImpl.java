package com.beck.dao;

import com.beck.entity.Product;
import com.beck.entity.User;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    private SessionFactory sessionFactory;

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean createUser(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(email) from User where email =:email", User.class);
        query.setParameter("email",user.getEmail());

        long count = (long) query.getSingleResult();

        if (count==0){
            sessionFactory.getCurrentSession().persist(user);
            logger.info("User was created " + user);
            return true;
        }

        logger.info("User is existed " + user);
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUser(String email, String password) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from User where email =:email and password =:password", User.class);
        query.setParameter("email",email);
        query.setParameter("password",password);

        User user = null;

        try{
            user = query.getSingleResult();
            logger.info("User by email and password was found " + user);
        }catch (NoResultException e){
            logger.info("User was not found");
        }
        return user;
    }

    @Override
    public void logout(User user) {
        MutationQuery query = sessionFactory.getCurrentSession().createMutationQuery("delete from BasketOrder where user =: user");
        query.setParameter("user",user);
        query.executeUpdate();
        logger.info("User was logout: " + user);

    }
}
