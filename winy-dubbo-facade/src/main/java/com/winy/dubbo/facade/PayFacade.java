package com.winy.dubbo.facade;

/**
 * 描述：支付dubbo服务
 *
 */
public interface PayFacade {

    /**
     * 支付订单
     * @param id
     */
    String doPayOrder(int id);


}
