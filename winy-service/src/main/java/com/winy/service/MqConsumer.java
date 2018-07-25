package com.winy.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Connection;
import com.rabbitmq.init.RabbitConnectionFactory;
import com.winy.core.StartResourceBoot;

/**
 * 描述：
 *
 * @author winy
 * @create_time 2018-07-24 14:09
 */
@Component
public class MqConsumer extends StartResourceBoot{


    /**
     * 启动方法
     */
    @Override
    protected void start_run() {
        try {
            Connection connection = RabbitConnectionFactory.getConnection();

            System.out.println("连接为：" + connection);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
