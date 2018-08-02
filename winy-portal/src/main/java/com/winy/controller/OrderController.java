package com.winy.controller;

import com.winy.common.ResponseVO;
import com.winy.model.OrderVO;
import com.winy.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述：
 *
 * @author winy
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 跳转到添加订单页面
     * @return
     */
    @RequestMapping("/toOrderPage")
    public ModelAndView toOrderPage() {

        return new ModelAndView("order/addOrder");
    }

    /**
     * 1. 订单保存后跳转到订单列表页面
     * 2. 订单30分钟未支付，则取消订单
     * @param orderVO
     * @return 页面重定向
     */
    @RequestMapping("/save")
    public ModelAndView saveOrder(OrderVO orderVO) {

        logger.info("参数：" + orderVO.toString());

        orderService.save(orderVO);

        ModelAndView mv = new ModelAndView("redirect:/order/list");

        return mv;
    }


    /**
     * 订单保存后跳转到订单列表页面
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView orderList() {

        List list = orderService.searchOrderAll();

        ModelAndView mv = new ModelAndView("order/orderList");

        ResponseVO responseVO = ResponseVO.getInstance().setResult(list);

        mv.addObject("responseVO",responseVO);

        return mv;
    }

    /**
     * 订单支付
     * @return
     */
    @RequestMapping("/doPay")
    @ResponseBody
    public ResponseVO doPay(int id) {

        orderService.doPay(id);

        ResponseVO responseVO = ResponseVO.getInstance();

        return responseVO;
    }




}
