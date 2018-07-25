package com.winy.service;

import com.winy.model.OrderVO;

import java.util.List;

/**
 * 描述：
 *
 * @author winy
 * @create_time 2018-07-11 10:07
 */
public interface OrderService {

    void save(OrderVO orderVO);

    OrderVO searchOrder(String orderNo);

    List searchOrderAll();


}
