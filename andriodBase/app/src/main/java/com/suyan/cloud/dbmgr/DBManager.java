package com.suyan.cloud.dbmgr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.suyan.cloud.dao.DaoMaster;
import com.suyan.cloud.dao.DaoSession;

public class DBManager {
    private static DBManager s_instance;
    private Context mContext;
    private DaoMaster.DevOpenHelper mOpenHelper;
    private static final String DB_NAME = "YunCangDB";
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DBManager() {

    }

    public static synchronized DBManager getInstance() {
        if (s_instance == null)
            s_instance = new DBManager();

        return s_instance;
    }

    public void init(Context context) {
        mContext = context;
//        mOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mOpenHelper == null) {
            mOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
        }
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mOpenHelper == null) {
            mOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
        }
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        return db;
    }


    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}