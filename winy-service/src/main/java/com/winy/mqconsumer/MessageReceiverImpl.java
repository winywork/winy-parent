package com.winy.mqconsumer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winy.dao.OrderDao;
import com.winy.model.OrderPO;
import com.winy.rabbitmq.MessageReceiver;

/**
 * 描述：消息具体实现类
 *
 * @author winy
 */
@Service
public class MessageReceiverImpl  implements MessageReceiver {

    @Autowired
    private OrderDao orderDao;

    private static Logger logger = LoggerFactory.getLogger(MessageReceiverImpl.class);


    /**
     * 判断是否已支付,如果还是待支付，则更新为已失效
     *
     * @param msg 具体消息
     */
    @Override
    public void doReceiveMsg(String msg) {

        logger.info("doReceiveMsg:"+msg);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // json转换为对象
                    ObjectMapper objectMapper = new ObjectMapper();
                    OrderPO orderPO = objectMapper.readValue(msg,OrderPO.class);

                    // 判断是否已支付,如果还是待支付，则更新为已失效
                    OrderPO searchPO = orderDao.searchOrderById(orderPO.getId());
                    if (searchPO != null && searchPO.getStatus() == 0) {
                        orderDao.updateOrderById(searchPO.getId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
