package com.beck.service;

import com.beck.dao.FinalOrderDAO;
import com.beck.entity.FinalOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FinalOrderServiceImpl implements FinalOrderService{

    private FinalOrderDAO finalOrderDAO;

    @Autowired
    public FinalOrderServiceImpl(FinalOrderDAO finalOrderDAO) {
        this.finalOrderDAO = finalOrderDAO;
    }

    @Override
    public List<FinalOrder> getListFinalOrder() {
        return finalOrderDAO.getListFinalOrder();
    }

    @Override
    @Transactional
    public void removeOrder(long id) {
        finalOrderDAO.removeOrder(id);

    }
}
