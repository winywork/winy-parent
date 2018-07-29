package com.winy.mqconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winy.core.StartResourceBoot;
import com.winy.rabbitmq.MqConsumerFacade;
import com.winy.rabbitmq.constants.MQConstant;

/**
 * 描述：订单相关消息消费实现类(系统启动时即执行)
 *
 * @author winy
 */
@Component
public class OrderMqConsumer extends StartResourceBoot{

    @Autowired
    private MqConsumerFacade mqConsumerFacade;


    /**
     * 启动方法
     */
    @Override
    protected void start_run() {

        System.out.println("mqconsumer start_run*********");

        mqConsumerFacade.acceptMsg(MQConstant.ORDER_LIVE_EXCHANGE,MQConstant.ORDER_LIVE_QUEUE,new MessageReceiverImpl());

    }



}
