package com.suyan.cloud.utils;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.view.Gravity;
import android.widget.Toast;


public class ToastUtil {

    @IntDef({Toast.LENGTH_SHORT, Toast.LENGTH_LONG})
    public @interface Duration {
    }

//    private static Toast toast = null;

    public static void showToast(String text, @Duration int length) {
        //先检查是否在主线程中运行，在进行处理
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Context context = com.suyan.cloud.AppContext.s_instance.getBaseContext();

            Toast  toast = Toast.makeText(context, text, length);

            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setText(text);
            toast.setDuration(length);

            toast.show();
        }
    }

    public static void showToast(int textResId, @Duration int length) {
        //先检查是否在主线程中运行，在进行处理
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Context context = com.suyan.cloud.AppContext.s_instance.getBaseContext();
            Toast toast = Toast.makeText(context, textResId, length);

            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setText(textResId);
            toast.setDuration(length);

            toast.show();
        }
    }

    public static void showToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    public static void showToast(int textId) {
        showToast(textId, Toast.LENGTH_SHORT);
    }
}
