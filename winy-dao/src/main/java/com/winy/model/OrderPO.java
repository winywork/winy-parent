package com.winy.model;

import java.util.Date;

/**
 * 描述：
 *
 * @author winy
 * @create_time 2018-07-12 13:40
 */
public class OrderPO {

    private int id;

    private String orderNo;

    private Date createTime;

    // 订单状态  '0 待支付  1 已支付  2 已失效'
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
