package com.mxgraph.server.biz.utils;


/**
 * 功能描述: 数据返回模型
 */
public class ReturnModel {


    public static final Integer SUCCESS = 200;

    public static final Integer FAILED = 500;

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 响应的消息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 可能会返回一下分页数据
     */
    private long total;


    public ReturnModel(Integer status, long total, Object data) {
        this.status = status;
        this.data = data;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public ReturnModel(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static ReturnModel build(Integer status, String msg, Object data) {
        return new ReturnModel(status, msg, data);
    }

    public static ReturnModel build(Integer status, long total, Object data) {
        return new ReturnModel(status,total,data);
    }
    public static ReturnModel build(Integer status, String msg) {
        return new ReturnModel(status, msg);
    }

    public static ReturnModel ok(String msg, Object data) {
        return ReturnModel.build(ReturnModel.SUCCESS, msg, data);
    }

    public static ReturnModel ok(String msg) {
        return ReturnModel.build(ReturnModel.SUCCESS, msg, null);
    }


    public static ReturnModel ok(int total,Object data) {
        return ReturnModel.build(ReturnModel.SUCCESS, total, data);
    }

    public static ReturnModel ok(Object data) {
        return ReturnModel.build(ReturnModel.SUCCESS, null, data);
    }

    public static ReturnModel error(String msg, Object data) {
        return ReturnModel.build(ReturnModel.FAILED, msg, data);
    }

    public static ReturnModel error(String msg) {
        return ReturnModel.build(ReturnModel.FAILED, msg, null);
    }

    public static ReturnModel error() {
        return ReturnModel.build(ReturnModel.FAILED, null, null);
    }

    public ReturnModel(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReturnModel{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
