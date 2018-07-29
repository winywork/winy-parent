package com.winy.rabbitmq;

/**
 * 描述：接收消息处理接口
 *
 * @author liuming
 * @create_time 2018-07-28 21:05
 */
public interface MessageReceiver {

    /**
     * 处理接收到的消息接口
     * @param msg 具体消息
     */
    void doReceiveMsg(String msg);

}
