package com.gt.common.domain;

import java.io.Serializable;

/**
 * @desc 统一返回格式
 */

public class DataGridResult implements Serializable {

    // 响应业务状态
    private String code;

    // 响应消息
    private String msg;

    // 响应中的数据的数量
    private Long count;

    // 响应中的数据
    private Object data;

    public DataGridResult() {

    }

    public DataGridResult(String code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static DataGridResult ok(Long count, Object data) {
        return new DataGridResult("0", "提交成功", count, data);
    }

    public static DataGridResult error() {
        return new DataGridResult("1", "ERROR", 0L, null);
    }

    public static DataGridResult errorWithMsg(String msg) {
        return new DataGridResult("1", msg, 0L, null);
    }

    public static DataGridResult successWithMsg(String msg) {
        return new DataGridResult("0", msg, 0L, null);
    }

    /**
     * @desc 推荐使用
     * @author zhukeyan
     * @date 2022/4/25
     */
    public static DataGridResult build(String code, String msg, Long count, Object data) {
        return new DataGridResult(code, msg, count, data);
    }

    public static DataGridResult success(Long count, Object data) {
        return new DataGridResult("0", "获取成功", count, data);
    }

    public static DataGridResult error(String err) {
        return new DataGridResult("1", err, 0L, null);
    }

}
