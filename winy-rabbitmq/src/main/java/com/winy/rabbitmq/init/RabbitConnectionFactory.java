package com.winy.rabbitmq.init;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 描述：配置信息初始化
 *
 * @author winy
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
