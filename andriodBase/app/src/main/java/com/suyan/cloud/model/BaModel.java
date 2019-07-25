package com.suyan.cloud.model;

import com.suyan.cloud.base.BaseModel;
import com.suyan.cloud.bean.response.LoginRes;
import com.suyan.cloud.network.ApiManager;
import com.suyan.cloud.network.IBaseCallback;
import com.suyan.cloud.network.ResponseTransformer;
import com.suyan.cloud.network.BaseDataResponse;
import com.suyan.cloud.network.exception.ApiException;
import com.suyan.cloud.network.schedules.SchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class BaModel extends BaseModel {

    public BaModel() {
        super();
    }


    //登录
    public void login(String username,String password, IBaseCallback<LoginRes> callback) {

        Observable<BaseDataResponse<LoginRes>> observable =
                ApiManager.getInstance().getUserEmpService().login(username,password);
        Disposable disposable = observable
                .compose(ResponseTransformer.handleResult())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribe(result -> {
                    // 处理数据 直接获取到FaceResponse<QueryQrScan>
                    callback.onResult(result);
                }, throwable -> {
                    // 处理异常
                    callback.onError((ApiException) throwable);
                });

        mDisposable.add(disposable);
    }

}
