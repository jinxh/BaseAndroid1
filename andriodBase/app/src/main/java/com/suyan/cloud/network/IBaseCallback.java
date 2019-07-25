package com.suyan.cloud.network;

import com.suyan.cloud.network.exception.ApiException;

public interface IBaseCallback<T> {
    void onResult(T result);

    void onError(ApiException throwable);
}
