package com.winy.serviceImpl;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winy.rabbitmq.MqSenderFacade;
import com.winy.rabbitmq.constants.MQConstant;
import com.winy.dao.OrderDao;
import com.winy.model.OrderPO;
import com.winy.model.OrderVO;
import com.winy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述：订单接口实现类
 *
 * @author winy
 * @create_time 2018-07-11 10:08
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MqSenderFacade mqSenderFacade;

    /**
     * 1. 订单保存后跳转到订单列表页面
     * 2. 订单30分钟未支付，则取消订单
     * @param orderVO
     * @return
     */
    @Override
    public void save(OrderVO orderVO) {

        try {
            // 1. 订单保存
            OrderPO po = new OrderPO();
            po.setOrderNo(orderVO.getOrderNo());
            po.setCreateTime(new Date());
            int num = orderDao.save(po);

            // 转换为json
            ObjectMapper objectMapper = new ObjectMapper();
            String msg = objectMapper.writeValueAsString(po);

            System.out.println(msg);

            // 2. 发送30分钟失效消息
            mqSenderFacade.sendTTLMsg(MQConstant.ORDER_DEAD_EXCHANGE,MQConstant.ORDER_LIVE_EXCHANGE,MQConstant.ORDER_DEAD_QUEUE,
                    MQConstant.EXCHANGE_MODEL_FANOUT,msg,2*1000*60);//2分钟测试

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public OrderVO searchOrder(String orderNo) {
        return null;
    }

    @Override
    public List searchOrderAll() {
        return orderDao.searchOrderAll();
    }


    @Override
    public void doPay(int id) {

        try {

            orderDao.dopay(id);
            System.out.println("支付成功");

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
