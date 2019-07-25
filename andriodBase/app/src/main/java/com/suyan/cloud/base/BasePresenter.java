package com.suyan.cloud.base;

public abstract class BasePresenter<T extends  BaseMvpView> {

    private T mView;

    protected T getView() {
        return mView;
    }

    public void setView(T view) {
        mView = view;
    }

    public abstract void destroy();

    public boolean viewIsActive() {
        return mView != null && mView.isActive();
    }
}
