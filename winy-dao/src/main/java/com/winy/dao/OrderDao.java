package com.winy.dao;

import com.winy.model.OrderPO;

import java.util.List;

/**
 * 描述：
 *
 * @author winy
 */
public interface OrderDao {

    int save(OrderPO orderPO);

    OrderPO searchOrder(String orderNo);

    List searchOrderAll();

    void dopay(int id);

    OrderPO searchOrderById(int id);

    void updateOrderById(int id);

}
