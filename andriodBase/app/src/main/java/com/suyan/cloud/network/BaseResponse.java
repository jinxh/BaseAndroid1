package com.suyan.cloud.network;

public class BaseResponse {
    protected String code; // 返回的code

    protected String msg; // message 可用来返回接口的说明

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
}
