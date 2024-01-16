package com.beck.dao;

import com.beck.entity.FinalOrder;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FinalOrderDaoImpl implements FinalOrderDAO{

    private SessionFactory sessionFactory;
    private static Logger logger = LoggerFactory.getLogger(BasketOrderDaoImpl.class);

    @Autowired
    public FinalOrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<FinalOrder> getListFinalOrder() {
        return sessionFactory.getCurrentSession().createQuery("from FinalOrder", FinalOrder.class).getResultList();
    }

    @Override
    public void removeOrder(long id) {
        FinalOrder finalOrder = sessionFactory.getCurrentSession().get(FinalOrder.class, id);
        sessionFactory.getCurrentSession().remove(finalOrder);
        logger.info("Final order was removed: " + finalOrder);
    }
}
