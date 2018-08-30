package com.winy.dubbo.facade.impl.order;

import com.winy.dubbo.facade.OrderFacade;
import org.springframework.stereotype.Service;

/**
 * 描述：订单服务提供者
 *
 */
@Service
public class OrderFacadeImpl implements OrderFacade{

    /**
     * 关闭订单
     *
     * @param id
     */
    @Override
    public String doCloseOrder(int id) {

        System.out.println("关闭订单服务，订单id:" + id);

        return "1";

    }
}
