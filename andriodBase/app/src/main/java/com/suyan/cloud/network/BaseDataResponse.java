package com.suyan.cloud.network;

public class BaseDataResponse<T> extends BaseResponse{

    private T data; // 具体的数据结果

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
