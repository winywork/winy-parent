package com.winy.rabbitmq.constants;

/**
 * 描述：消息队列常量
 *
 * @author liuming
 * @create_time 2018-07-26 17:27
 */
public class MQConstant {

    private MQConstant(){}

    // 交换机模式 fanout
    public static final String EXCHANGE_MODEL_FANOUT = "fanout";

    // 交换机模式 direct
    public static final String EXCHANGE_MODEL_DIRECT = "direct";

    // 交换机模式 topic
    public static final String EXCHANGE_MODEL_TOPIC = "topic";

    // 死信交换机
    public static final String ORDER_DEAD_EXCHANGE = "order.dead.exchange";

    // 死信队列
    public static final String ORDER_DEAD_QUEUE = "order.dead.queue";

    // 转发交换机
    public static final String ORDER_LIVE_EXCHANGE = "order.live.exchange";

    // 转发队列
    public static final String ORDER_LIVE_QUEUE = "order.live.queue";






}
