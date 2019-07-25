package com.suyan.cloud;

import android.content.Intent;
import android.os.Bundle;

import com.suyan.cloud.base.BaseActivity;
import com.suyan.cloud.ui.LoginActivity;
import com.suyan.cloud.utils.PreferenceUtils;

/**
 * Created by bianyin on 2019/3/19.
 */
public class PrepareActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isLogined  =PreferenceUtils.getInstance(PrepareActivity.this).getBooleanParam(PreferenceUtils.IS_LOGIN,false);

        if (isLogined) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

}
