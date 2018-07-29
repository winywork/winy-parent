package com.winy.rabbitmq;

/**
 * 描述：消息消费(接收处理)接口
 *
 * @author winy
 */
public interface MqConsumerFacade {

    /**
     * 接收绑定到该exchange上队列的消息
     * @param exchange 交换机
     * @param queue 接收队列
     * @param messageReceiver 消息接收接口
     */
    void acceptMsg(String exchange, String queue, MessageReceiver messageReceiver);


}
