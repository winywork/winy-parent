package com.winy.rabbitmq.init;

import com.rabbitmq.client.Address;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 描述：启动的时候自动加载rabbitmq配置，获取连接
 *
 * @author winy
 * @create_time 2018-07-24 13:12
 */
public class RabbitMQConfiguration implements InitializingBean{

    @Value("${rabbit-username:admin}")
    private String username;

    @Value("${rabbit-password:123456}")
    private String password;

    @Value("${rabbit-host:192.168.95.129:5672,192.168.95.130:5672}")
    private String rabbitServers;


    // 各节点地址
    List<Address> addressList = new ArrayList<Address>();


    /**
     * 启动的时候自动加载rabbitmq配置，获取连接
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        doConfigInit();
    }


    /**
     * 配置初始化
     */
    private void doConfigInit() {

        String[] nodes = rabbitServers.split(",");
        Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");
        for (String node : nodes) {
            boolean isIpPort = p.matcher(node).matches();
            if (!isIpPort) {
                throw new IllegalArgumentException("ip 或 port 不合法");
            }
            String[] ipAndPort = node.split(":");
            Address addr = new Address(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
            addressList.add(addr);
        }
        RabbitConnectionFactory.factory.setUsername(username);
        RabbitConnectionFactory.factory.setPassword(password);

        // 此设置很重要，自动重连发现，随机连接配置节点中的一个节点
        RabbitConnectionFactory.factory.setAutomaticRecoveryEnabled(true);

        RabbitConnectionFactory.setConfig(this);

    }


    public List<Address> getAddress(){
        return this.addressList;
    }


    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
