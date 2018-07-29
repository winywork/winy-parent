package com.winy.rabbitmq;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.winy.rabbitmq.constants.MQConstant;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.winy.rabbitmq.init.RabbitConnectionFactory;

/**
 * 描述：消息消费实现类
 *
 * @author winy
 * @create_time 2018-07-14 16:53
 */
@Service
public class MqConsumerFacadeImpl implements MqConsumerFacade {

    // 通道
    private  Channel channel;

    // 连接
    private  Connection connection;


    /**
     * 接收绑定到该exchange上队列的消息
     * @param exchange 交换机
     * @param queue 接收队列
     * @param messageReceiver 消息接收接口
     * @return 接收到的消息
     */
     @Override
     public void acceptMsg(String exchange, String queue, MessageReceiver messageReceiver) {

        try {

            connection = RabbitConnectionFactory.getConnection();

            channel = connection.createChannel();

            // 声明交换机
            channel.exchangeDeclare(exchange, MQConstant.EXCHANGE_MODEL_FANOUT);

            // 声明队列
            channel.queueDeclare(queue,true,false,false,null);

            // 绑定
            channel.queueBind(queue,exchange,"");

            // 接收消息消费
            channel.basicConsume(queue,true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body);

                    System.out.println("接收到的消息：" + msg + "-----接收时间：" + new Date());

                    // 消息具体处理
                    messageReceiver.doReceiveMsg(msg);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

     }
}
