package com.winy.rabbitmq;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.winy.rabbitmq.init.RabbitConnectionFactory;

/**
 * 描述：消息发送实现类
 *
 * @author winy
 */
@Service
public class MqSenderFacadeImpl implements MqSenderFacade {


    Logger logger = LoggerFactory.getLogger(MqSenderFacadeImpl.class);

    // 通道
    private  Channel channel;

    // 连接
    private  Connection connection;


    /**
     * 发送消息(待用)
     *
     * @param exchange 交换机
     * @param queue    队列
     * @param model    exchange 模式
     *              fanout:订阅模式(Fanout Exchange)
     *              direct:路由模式(Direct Exchange)
     *              topic:通配符模式(Topic Exchange)
     * @param msg      发送的具体消息
     */
    @Override
    public void sendMsg(String exchange, String queue, String model, String msg) {
        try {
            // 声明exchange
            channel.exchangeDeclare(exchange,model);

            channel.basicPublish(exchange,"",null,msg.getBytes("utf-8"));

            logger.info("已发送消息：" + msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
    @Override
    public void sendTTLMsg(String deadexchange, String liveexchange, String deadqueue,String model, String msg,int ttltime) {
        try {

            connection = RabbitConnectionFactory.getConnection();

            channel = connection.createChannel();

            // 声明exchange
            channel.exchangeDeclare(deadexchange,model);

            // 队列和交换机绑定
            channel.queueBind(deadqueue,deadexchange,"");

            // 设置死信转发参数
            Map<String,Object> args = new HashMap<>();
            // 过期时间  毫秒数
            args.put("x-message-ttl",ttltime);

            // 死信转发
            args.put("x-dead-letter-exchange", liveexchange);

            // 声明死信队列
            channel.queueDeclare(deadqueue,false,false,false,args);

            // 消息发送
            channel.basicPublish(deadexchange,"",null,msg.getBytes("utf-8"));

            logger.info("已发送消息：" + msg + "-----发送时间：" + new Date());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
