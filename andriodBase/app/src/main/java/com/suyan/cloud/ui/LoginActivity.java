package com.suyan.cloud.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.suyan.cloud.MainActivity;
import com.suyan.cloud.R;
import com.suyan.cloud.base.BaseActivity;
import com.suyan.cloud.bean.UserInfoBean;
import com.suyan.cloud.bean.response.LoginRes;
import com.suyan.cloud.model.BaModel;
import com.suyan.cloud.network.IBaseCallback;
import com.suyan.cloud.network.exception.ApiException;
import com.suyan.cloud.service.UserService;
import com.suyan.cloud.utils.PreferenceUtils;
import com.suyan.cloud.utils.SharePreferencesHelper;
import com.suyan.cloud.utils.ToastUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView mPhoneTv;
    private TextView mSwitchUserTv;
    private ImageView mSwitchUserImg;
    private EditText mPhoneEt;
    private EditText mVerifyCodeEt;
    private TextView mSubmitView;

    private ImageView mFingerImg;

    private boolean mUsingFingerPrint = true;

    private Disposable mInternalDisposable = null;
    private int mCount = -1;
    private BaModel mBaseModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPhoneTv = findViewById(R.id.tv_phone_num);
        mFingerImg = findViewById(R.id.img_fingerprint);
        mFingerImg.setOnClickListener(this);

        mSwitchUserTv = findViewById(R.id.tv_switch_user);
        mSwitchUserImg = findViewById(R.id.img_switch_user);
        mSwitchUserTv.setOnClickListener(this);
        mSwitchUserImg.setOnClickListener(this);

        mPhoneEt = findViewById(R.id.et_phone);
        mVerifyCodeEt = findViewById(R.id.et_verify_code);
        mSubmitView = findViewById(R.id.tv_submit);
        mSubmitView.setOnClickListener(this);
        switchWay(true);
        mBaseModel = new BaModel();

        String userName = PreferenceUtils.getInstance(LoginActivity.this).getStringParam(PreferenceUtils.USER_NAME);
        if(!TextUtils.isEmpty(userName)){
            mPhoneEt.setText(userName);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaseModel.disposeAll();
        cancelInternal();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_switch_user:
            case R.id.img_switch_user:
                switchWay(!mUsingFingerPrint);
                break;

            case R.id.tv_submit:
                showLoading();
                String userName = mPhoneEt.getText().toString();
                String password = mVerifyCodeEt.getText().toString();

                if(TextUtils.isEmpty(userName)){
                    ToastUtil.showToast("请输入用户名");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    ToastUtil.showToast("请输入密码");
                    return;
                }
                mBaseModel.login(userName, password, new IBaseCallback<LoginRes>() {
                    @Override
                    public void onResult(LoginRes result) {
                        dismissLoading();
                        UserService userService = UserService.getInstance();
                        UserInfoBean mUser = new UserInfoBean();
                        userService.setUser(mUser);
                        PreferenceUtils.getInstance(LoginActivity.this).saveParam(PreferenceUtils.USER_NAME,userName);
                        PreferenceUtils.getInstance(LoginActivity.this).saveParam(PreferenceUtils.IS_LOGIN,true);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(ApiException throwable) {
                        dismissLoading();
                        ToastUtil.showToast(throwable.getDisplayMessage());

                    }
                });

                break;


            case R.id.img_fingerprint:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;

            default:
                break;
        }
    }


//
//    private void startCountDown() {
//        mCount = 60;
//        mGetVerifyTv.setClickable(false);
//        String sFormat = getResources().getString(R.string.get_verify_code1);
//        String sFinal = String.format(sFormat, mCount);
//        mGetVerifyTv.setText(sFinal);
//        Observable.interval(1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable disposable) {
//                        mInternalDisposable = disposable;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Long number) {
//
//                        if (mCount <= 0) {
//                            mGetVerifyTv.setText(R.string.get_verify_code);
//                            mGetVerifyTv.setClickable(true);
//                            cancelInternal();
//                        } else {
//                            mCount--;
//                            String sFormat = getResources().getString(R.string.get_verify_code1);
//                            String sFinal = String.format(sFormat, mCount);
//                            mGetVerifyTv.setText(sFinal);
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        mGetVerifyTv.setClickable(true);
//                        cancelInternal();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mGetVerifyTv.setClickable(true);
//                        cancelInternal();
//                    }
//                });
//    }

    private void cancelInternal(){
        if(mInternalDisposable != null && !mInternalDisposable.isDisposed()){
            mInternalDisposable.dispose();
        }
    }

    private void switchWay(boolean use) {
        mUsingFingerPrint = use;
        if (mUsingFingerPrint) {
            mPhoneEt.setVisibility(View.GONE);
            mVerifyCodeEt.setVisibility(View.GONE);
//            mGetVerifyTv.setVisibility(View.GONE);
            mSubmitView.setVisibility(View.GONE);
            mPhoneTv.setVisibility(View.VISIBLE);
            mFingerImg.setVisibility(View.VISIBLE);
            mSwitchUserTv.setText(R.string.switch_phone_num);
        } else {
            mPhoneEt.setVisibility(View.VISIBLE);
            mVerifyCodeEt.setVisibility(View.VISIBLE);
//            mGetVerifyTv.setVisibility(View.VISIBLE);
            mSubmitView.setVisibility(View.VISIBLE);
            mPhoneTv.setVisibility(View.GONE);
            mFingerImg.setVisibility(View.GONE);
            mSwitchUserTv.setVisibility(View.GONE);
            mSwitchUserImg.setVisibility(View.GONE);
            mSwitchUserTv.setText(R.string.switch_finger_print);
        }
    }
}
