package com.winy.serviceImpl;

import com.winy.dao.OrderDao;
import com.winy.model.OrderPO;
import com.winy.model.OrderVO;
import com.winy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述：
 *
 * @author winy
 * @create_time 2018-07-11 10:08
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;


    public void save(OrderVO orderVO) {
        OrderPO po = new OrderPO();
        po.setOrderNo(orderVO.getOrderNo());
        po.setCreateTime(new Date());

        orderDao.save(po);

    }

    public OrderVO searchOrder(String orderNo) {
        return null;
    }

    public List searchOrderAll() {
        return orderDao.searchOrderAll();
    }
}
