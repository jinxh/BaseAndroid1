package com.suyan.cloud.network;

import com.suyan.cloud.network.exception.ApiException;
import com.suyan.cloud.network.exception.CustomException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class ResponseTransformer {

    public static <T> ObservableTransformer<BaseDataResponse<T>, T> handleResult() {
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<>())
                .flatMap(new ResponseFunction<>());
    }
    

    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param <T>
     */
    private static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends BaseDataResponse<T>>> {

        @Override
        public ObservableSource<? extends BaseDataResponse<T>> apply(Throwable throwable) throws Exception {
            return Observable.error(CustomException.handleException(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<BaseDataResponse<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(BaseDataResponse<T> tResponse) throws Exception {
            String code = tResponse.getCode();
            String message = tResponse.getMsg();
            if ("200".equals(code)) {
                return Observable.just(tResponse.getData());
            } else {
                int res = CustomException.UNKNOWN;
                try {
                    res = Integer.parseInt(code);
                } catch (NumberFormatException e) {
                    res = CustomException.UNKNOWN;
                }
                return Observable.error(new ApiException(res, message));
            }
        }
    }
}