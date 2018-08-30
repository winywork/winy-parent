package com.winy.service;

import com.winy.model.OrderVO;

import java.util.List;

/**
 * 描述：订单接口
 *
 * @author winy
 */
public interface OrderService {

    /**
     * 1. 订单保存后跳转到订单列表页面
     * 2. 订单30分钟未支付，则取消订单
     * @param orderVO
     */
    void save(OrderVO orderVO);

    /**
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
    OrderVO searchOrder(String orderNo);

    /**
     * 查询所有订单
     * @return
     */
    List searchOrderAll();

    /**
     * 订单支付
     * @param id
     */
    void doPay(int id);

    /**
     * 取消订单
     * @param id
     */
    void doCancelOrder(int id);


}
