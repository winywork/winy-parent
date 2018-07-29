package com.winy.rabbitmq;

/**
 * 描述：接收消息处理接口
 *
 * @author winy
 */
public interface MessageReceiver {

    /**
     * 处理接收到的消息接口
     * @param msg 具体消息
     */
    void doReceiveMsg(String msg);

}
