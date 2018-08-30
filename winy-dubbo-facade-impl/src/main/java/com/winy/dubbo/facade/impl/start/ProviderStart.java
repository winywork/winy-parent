package com.winy.dubbo.facade.impl.start;

import com.alibaba.dubbo.container.Main;

/**
 * 描述：jar启动类
 *
 */
public class ProviderStart {

    public static void main(String[] args) {

        System.setProperty("dubbo.spring.config", "spring-dubbo-provider.xml");

        System.out.println("winy-dubbo 已启动！");

        Main.main(args);
    }

}
