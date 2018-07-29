package com.winy.core;

import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * 描述：spring ApplicationListener
 *
 * @author winy
 * @create_time 2018-07-23 19:30
 */
@Service
public class StartManager implements ApplicationListener<ContextRefreshedEvent>{

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {


        // 在web项目中（spring mvc），系统会存在两个容器，一个是root application context
        // ,另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
        // 这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免这个问题，我们可以只在root
        // application context初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理。
        if (event.getApplicationContext().getParent() == null) {
            // 需要实现的功能

            Map<String, StartResourceBoot> map = event.getApplicationContext().getBeansOfType(StartResourceBoot.class);

            if (map == null) return;

            for (StartResourceBoot boot : map.values()) {
                boot.start_run();
            }
        }
    }
}
