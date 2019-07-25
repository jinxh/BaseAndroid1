package com.suyan.cloud.base;

import io.reactivex.disposables.CompositeDisposable;

public class BaseModel {
    protected CompositeDisposable mDisposable;

    public BaseModel() {
        mDisposable = new CompositeDisposable();
    }

    public void disposeAll(){
        mDisposable.dispose();
    }
}
