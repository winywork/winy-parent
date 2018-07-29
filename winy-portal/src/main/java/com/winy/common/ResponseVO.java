package com.winy.common;

import java.io.Serializable;

/**
 * 描述：通用返回对象
 *
 * @author winy
 * @create_time 2018-07-13 14:27
 */
public class ResponseVO<T> implements Serializable{

    // 状态 0,正常  -1 其他
    private int code = 0;

    // 异常描述
    private String desc = "调用成功";

    private T result;

    public int getCode() {
        return code;
    }

    public ResponseVO<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ResponseVO<T> setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public T getResult() {
        return result;
    }

    public ResponseVO<T> setResult(T result) {
        this.result = result;
        return this;
    }


    /**
     * 返回通用对象
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T>  getInstance() {
       return new ResponseVO<T>();
    }
}
