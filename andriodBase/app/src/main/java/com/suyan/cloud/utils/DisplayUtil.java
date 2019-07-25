package com.suyan.cloud.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.suyan.cloud.log.LogUtils;

public class DisplayUtil {
    public static ScreenWH getScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            LogUtils.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
                    ",densityDpi = " + densityDpi + "\n" +
                    ",density = " + density + ",scaledDensity = " + scaledDensity);
            return new ScreenWH(outMetrics.widthPixels, outMetrics.heightPixels);
        }
        return null;
    }

//    public static void getRealScreenRelatedInformation(Context context) {
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        if (windowManager != null) {
//            DisplayMetrics outMetrics = new DisplayMetrics();
//            windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
//            int widthPixels = outMetrics.widthPixels;
//            int heightPixels = outMetrics.heightPixels;
//            int densityDpi = outMetrics.densityDpi;
//            float density = outMetrics.density;
//            float scaledDensity = outMetrics.scaledDensity;
//            //可用显示大小的绝对宽度（以像素为单位）。
//            //可用显示大小的绝对高度（以像素为单位）。
//            //屏幕密度表示为每英寸点数。
//            //显示器的逻辑密度。
//            //显示屏上显示的字体缩放系数。
//            LogUtils.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
//                    ",densityDpi = " + densityDpi + "\n" +
//                    ",density = " + density + ",scaledDensity = " + scaledDensity);
//        }
//    }

    public static class ScreenWH {
        public ScreenWH(int w, int h) {
            width  = w;
            height = h;
        }
        public int width;
        public int height;
    }
}
