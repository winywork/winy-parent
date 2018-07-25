package com.winy.controller;

import com.winy.common.ResponseVO;
import com.winy.model.OrderVO;
import com.winy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述：
 *
 * @author winy
 * @create_time 2018-07-11 10:06
 */
@Controller
@RequestMapping("/order")
public class OrderController {

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
     * 订单保存后跳转到订单列表页面
     * @param orderVO
     * @return
     */
    @RequestMapping("/save")
    public ModelAndView saveOrder(OrderVO orderVO) {
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

}
