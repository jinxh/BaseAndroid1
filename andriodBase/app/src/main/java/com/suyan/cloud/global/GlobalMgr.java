package com.suyan.cloud.global;


import com.suyan.cloud.AppContext;
import com.suyan.cloud.utils.SharePreferencesHelper;


/**
 * Created by bianyin on 2019/4/26.
 */
public class GlobalMgr {
    public static GlobalMgr s_instance = null;

    private GlobalMgr() {

    }

    public static synchronized GlobalMgr getInstance() {
        if (s_instance == null) {
            s_instance = new GlobalMgr();
        }
        return s_instance;
    }

    public void init() {
        SharePreferencesHelper sharePreferencesHelper = new SharePreferencesHelper(AppContext.s_instance);

    }



}
