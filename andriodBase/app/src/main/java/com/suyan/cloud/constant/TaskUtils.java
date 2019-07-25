package com.suyan.cloud.constant;

/**
 * Created by bianyin on 2019/5/21.
 */
public class TaskUtils {
    public static String getTaskStatus(int status) {
        String strStatus = "";
        switch (status) {
            case TaskConstant.DAISHOULI:
                strStatus = "待受理";
                break;

            case TaskConstant.ZUOYEZHONG:
                strStatus = "作业中";
                break;

            case TaskConstant.DAIFUHE:
                strStatus = "待复核";
                break;

            case TaskConstant.YIWANCHENG:
                strStatus = "已完成";
                break;
        }

        return strStatus;
    }

    public static String getShuttleMode(int mode) {
        String strStatus = "";
        switch (mode) {
            case 0:
                strStatus = "-";
                break;
            case TaskConstant.SUTTLE_ZHANNEI:
                strStatus = "站内短驳";
                break;

            case TaskConstant.SUTTLE_ZHANWAI:
                strStatus = "站外短驳";
                break;
        }

        return strStatus;
    }

    public static String getTaskType(int type) {
        String strType = "";
        switch (type) {
            case TaskConstant.XIECHE:
                strType = "卸车";
                break;

            case TaskConstant.YUYANSHOU:
                strType = "预验收";
                break;

            case TaskConstant.TAOXIANG:
                strType = "掏箱";
                break;

            case TaskConstant.JIZHUANGXIANGFAYUN:
                strType = "集装箱发运";
                break;

            case TaskConstant.DAOHUOYIWEI:
                strType = "到货";
                break;

            case TaskConstant.KUCUNYIWEI:
                strType = "库存";
                break;

            case TaskConstant.JIZHUANGXIANGYIWEI:
                strType = "集装箱";
                break;

            case TaskConstant.FAHUO:
                strType = "发货";
                break;

            case TaskConstant.CANGDAN:
                strType = "仓单";
                break;

            case TaskConstant.PANDIAN:
                strType = "盘点";
                break;

            default:
                break;
        }
        return strType;
    }

}
