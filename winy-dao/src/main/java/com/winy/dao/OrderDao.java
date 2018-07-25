package com.winy.dao;

import com.winy.model.OrderPO;

import java.util.List;

/**
 * 描述：
 *
 * @author winy
 * @create_time 2018-07-12 13:38
 */
public interface OrderDao {

    void save(OrderPO orderPO);

    OrderPO searchOrder(String orderNo);

    List searchOrderAll();

}
