package com.suyan.cloud;

import android.support.multidex.MultiDexApplication;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.suyan.cloud.dbmgr.DBManager;
import com.suyan.cloud.global.GlobalMgr;
import com.suyan.cloud.log.LogUtils;
import com.suyan.cloud.network.NetWorkManager;

public class AppContext extends MultiDexApplication {

    public static AppContext s_instance;
    private GlobalMgr mGlobalMgr;

    @Override
    public void onCreate() {
        super.onCreate();
        s_instance = this;
        Beta.autoCheckUpgrade = true;
        Bugly.init(getApplicationContext(), "9f0fc2bfa6", false);
        ZXingLibrary.initDisplayOpinion(this);
        initLog();
        initManager();
        DBManager.getInstance().init(this);
        NetWorkManager.getInstance().init();

    }

    private void initManager() {
        GlobalMgr.getInstance().init();
    }

    private void initLog() {
        LogUtils.Config config = LogUtils.getConfig()
                .setLogSwitch(BuildConfig.DEBUG)// 设置 log 总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置 log 全局标签，默认为空
                // 当全局标签不为空时，我们输出的 log 全部为该 tag，
                // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setLogHeadSwitch(true)// 设置 log 头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印 log 时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为"util"，即写入文件为"util-MM-dd.txt"
                .setBorderSwitch(false)// 输出日志是否带边框开关，默认开
                .setConsoleFilter(LogUtils.V)// log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setBuglyFilter(LogUtils.I)
                .setFileFilter(LogUtils.V)// log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setStackDeep(1);// log 栈深度，默认为 1
        LogUtils.d(config.toString());
    }
}
