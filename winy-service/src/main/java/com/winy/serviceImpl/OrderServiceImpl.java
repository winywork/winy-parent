package com.winy.serviceImpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winy.dao.OrderDao;
import com.winy.dubbo.facade.OrderFacade;
import com.winy.model.OrderPO;
import com.winy.model.OrderVO;
import com.winy.rabbitmq.MqSenderFacade;
import com.winy.rabbitmq.constants.MQConstant;
import com.winy.service.OrderService;

/**
 * 描述：订单接口实现类
 *
 * @author winy
 */
@Service
public class OrderServiceImpl implements OrderService{

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MqSenderFacade mqSenderFacade;

    @Autowired
    private OrderFacade orderFacade;

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

            logger.info(msg);

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

            logger.info(String.valueOf(id));

            orderDao.dopay(id);

            logger.info("支付成功");

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 取消订单
     *
     * @param id
     */
    @Override
    public void doCancelOrder(int id) {
        logger.info("dubbo 服务 orderFacade 调用开始，id= " + id);

        String ret = orderFacade.doCloseOrder(id);

        logger.info("dubbo 服务 orderFacade 调用结束，id= " + id + "返回值：ret=" + ret);

    }
}
