package com.winy.core;

/**
 * 任何继承了ResourceBooter的子类都具有了系统启动后自动运行run方法的能力
 * 当然这一切都是基于spring的，所以你的子类要注入成一个spring bean。
 * @author winy
 */
public abstract class StartResourceBoot {

    /**
     * 启动方法
     */
    protected abstract void start_run();
}
