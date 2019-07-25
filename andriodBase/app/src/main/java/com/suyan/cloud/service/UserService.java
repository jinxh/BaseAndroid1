package com.suyan.cloud.service;

import com.suyan.cloud.bean.UserInfoBean;
import com.suyan.cloud.dbmgr.UserSql;
import com.suyan.cloud.dbmgr.entity.UserBean;
import com.suyan.cloud.utils.PreferenceUtils;

/**
 * Created by bianyin on 2019/3/19.
 */
public class UserService {
    private static UserService s_instance = null;
    private static UserInfoBean mUser;

    private UserService() {

    }

    public static synchronized UserService getInstance() {
        if (s_instance == null) {
            s_instance = new UserService();
        }

        return s_instance;
    }

    public UserInfoBean getUser() {
        if(mUser==null){
            mUser = new UserInfoBean();
            String userName = PreferenceUtils.getInstance(com.suyan.cloud.AppContext.s_instance).getStringParam(PreferenceUtils.USER_NAME);
            UserBean userBean =UserSql.getInstance().getList(userName).get(0);
            mUser.setOuid(userBean.getOuid());
            mUser.setUserId(userBean.getUserId());
            mUser.setUsername(userBean.getUserName());
            mUser.setName(userBean.getName());
            mUser.setRealUserId(userBean.getRealUserId());
            mUser.setOuName(userBean.getOuName());
        }
        return mUser;
    }

    public void setUser(UserInfoBean bean) {
        mUser = bean;
        UserBean userBean = new UserBean();
        userBean.setOuid(bean.getOuid());
        userBean.setUserId(bean.getUserId());
        userBean.setUserName(bean.getUsername());
        userBean.setName(bean.getName());
        userBean.setOuName(bean.getOuName());
        userBean.setRealUserId(bean.getRealUserId());
        UserSql.getInstance().updateOrInsert(userBean);
    }
}
