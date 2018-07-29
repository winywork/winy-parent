package com.winy.service;

import com.winy.model.OrderVO;

import java.util.List;

/**
 * 描述：订单接口
 *
 * @author winy
 * @create_time 2018-07-11 10:07
 */
public interface OrderService {

    /**
     * 1. 订单保存后跳转到订单列表页面
     * 2. 订单30分钟未支付，则取消订单
     * @param orderVO
     */
    void save(OrderVO orderVO);

    OrderVO searchOrder(String orderNo);

    List searchOrderAll();


    void doPay(int id);


}
