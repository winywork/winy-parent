package com.winy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：
 *
 * @author winy
 * @create_time 2018-07-10 16:16
 */
public class WinyServlet extends HttpServlet {

    /**
     * 只会第一次请求时会执行(全局变量初始化)
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("参数初始化 init");
    }

    /**
     * 核心处理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("请求转发 service");
        this.doGet(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("具体请求 doGet");
    }


    /**
     * 断开连接时执行
     */
    @Override
    public void destroy() {
        System.out.println("请求销毁 destroy");
    }


}
