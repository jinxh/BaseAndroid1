package com.suyan.cloud.base;


import com.suyan.cloud.mvp.MvpView;

/**
 * Created by jinxh on 16/6/15.
 */
public interface MyBaseMvpView extends MvpView {

    void showMessage(int res);

    void showMessage(CharSequence text);

    void showLoading();

    void dismissLoading();
}
