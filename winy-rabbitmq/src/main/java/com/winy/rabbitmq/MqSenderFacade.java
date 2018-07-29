package com.winy.rabbitmq;

/**
 * 描述：消息发送接口
 *
 * @author winy
 * @create_time 2018-07-14 16:52
 */
public interface MqSenderFacade {

    /**
     * 发送消息
     * @param exchange 交换机
     * @param queue 队列
     * @param model exchange 模式
     *              fanout:订阅模式(Fanout Exchange)
     *              direct:路由模式(Direct Exchange)
     *              topic:通配符模式(Topic Exchange)
     * @param msg 发送的具体消息
     */
    void sendMsg(String exchange,String queue,String model,String msg);


    /**
     * 发送延时消息
     *
     * @param deadexchange 死信交换机
     * @param liveexchange 转换交换机
     * @param deadqueue    死信队列
     * @param model    exchange 模式
     *              fanout:订阅模式(Fanout Exchange)
     *              direct:路由模式(Direct Exchange)
     *              topic:通配符模式(Topic Exchange)
     * @param msg      发送的具体消息
     */
    void sendTTLMsg(String deadexchange, String liveexchange, String deadqueue,String model, String msg,int ttltime);


}
