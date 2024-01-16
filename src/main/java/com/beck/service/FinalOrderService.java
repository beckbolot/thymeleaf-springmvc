package com.beck.service;

import com.beck.entity.FinalOrder;

import java.util.List;

public interface FinalOrderService {

    List<FinalOrder> getListFinalOrder();

    void removeOrder(long id);
}
