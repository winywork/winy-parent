package com.winy.dubbo.facade.impl.order;

import com.winy.dubbo.facade.PayFacade;
import org.springframework.stereotype.Service;

/**
 * 描述：支付订单
 *
 */
@Service
public class PayFacadeImpl implements PayFacade{

    /**
     * 支付订单
     *
     * @param id
     */
    @Override
    public String doPayOrder(int id) {
        return null;
    }
}
