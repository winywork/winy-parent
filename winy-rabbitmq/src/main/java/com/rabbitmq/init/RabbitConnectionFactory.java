package com.rabbitmq.init;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 描述：
 *
 * @author winy
 * @create_time 2018-07-24 13:30
 */
public class RabbitConnectionFactory {

    static ConnectionFactory factory = new ConnectionFactory();

    private static RabbitMQConfiguration config;

    public static Connection getConnection() throws IOException, TimeoutException {
        return factory.newConnection(config.getAddress());
    }

    public static RabbitMQConfiguration getConfig() {
        return config;
    }

    public static void setConfig(RabbitMQConfiguration config) {
        RabbitConnectionFactory.config = config;
    }
}
