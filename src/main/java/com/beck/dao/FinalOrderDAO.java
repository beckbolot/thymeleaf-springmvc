package com.beck.dao;

import com.beck.entity.FinalOrder;

import java.util.List;

public interface FinalOrderDAO {

    List<FinalOrder> getListFinalOrder();

    void removeOrder(long id);
}
