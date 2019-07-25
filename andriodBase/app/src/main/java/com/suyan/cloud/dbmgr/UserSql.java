package com.suyan.cloud.dbmgr;

import com.suyan.cloud.dao.UserBeanDao;
import com.suyan.cloud.dbmgr.entity.UserBean;

import java.util.List;

/**
 * Created by bianyin on 2019/4/27.
 */
public class UserSql {
    private static UserSql instance;
    private UserBeanDao mUserBeanDao;

    private UserSql() {
        mUserBeanDao = DBManager.getInstance().getDaoSession().getUserBeanDao();
    }

    public synchronized static UserSql getInstance(){
        if(instance == null){
            synchronized (UserSql.class){
                instance = new UserSql();
            }
        }
        return instance;
    }


    public void updateOrInsert(UserBean userBean) {
        synchronized (this) {
            List<UserBean> list = getList(userBean.getUserName());
            if(list.size()==0){
                mUserBeanDao.insert(userBean);
            }else {
                UserBean dbBean = list.get(0);
                userBean.setId(dbBean.getId());
                mUserBeanDao.update(userBean);
            }
        }
    }

    public List<UserBean> getList(String userName) {
        synchronized (this) {
            return mUserBeanDao.queryBuilder()
                    .where(UserBeanDao.Properties.UserName.eq(userName))
                    .list();
        }
    }
}
