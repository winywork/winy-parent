package com.winy.dubbo.facade;

/**
 * 描述：订单dubbo服务
 *
 */
public interface OrderFacade {

    /**
     * 关闭订单
     * @param id
     */
    String doCloseOrder(int id);


}
