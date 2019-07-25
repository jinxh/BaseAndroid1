package com.suyan.cloud.base;


/**
 * Created by bianyin on 2019/4/27.
 */

public interface IBaseCB<T> {
    void onResult(T result);

    void onError(Throwable throwable);
}

