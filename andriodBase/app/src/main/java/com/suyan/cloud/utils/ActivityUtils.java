package com.suyan.cloud.utils;


import com.suyan.cloud.base.BaseActivity;
import com.suyan.cloud.base.BaseDialogFragment;
import com.suyan.cloud.base.BaseFragment;
import com.suyan.cloud.log.LogUtils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;

public class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity(@NonNull BaseActivity activity,
                                             @NonNull BaseFragment fragment, int frameId) {
        activity.getSupportFragmentManager().beginTransaction().add(frameId, fragment).commit();
    }

    public static void removeFragment(@NonNull BaseActivity activity,
                                      String tag) {

        FragmentManager fragmentManager = activity.getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            LogUtils.i("ActivityUtils", "removeFragment");
            transaction.remove(fragment);
        }
        transaction.commit();
    }

    public static void removeFragment(@NonNull BaseActivity activity,
                                      int id) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentById(id);
        if (fragment != null) {
            LogUtils.i("ActivityUtils", "removeFragment");
            transaction.remove(fragment);
        }
        transaction.commit();
    }

    public static void showDialogFragment(@NonNull BaseDialogFragment dialogFragment, @NonNull BaseActivity activity, String tag) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            transaction.remove(fragment);
        }
        //transaction.commit();
        dialogFragment.show(transaction, tag);
    }

    public static boolean isFragmentShowing(@NonNull BaseActivity activity, String tag) {
        Fragment fragment = activity.getFragmentManager().findFragmentByTag(tag);
        return fragment != null;
    }

    public static boolean isFragmentShowing(@NonNull BaseActivity activity, int id) {
        Fragment fragment = activity.getFragmentManager().findFragmentById(id);
        return fragment != null;
    }

    public static void showFragment(@NonNull BaseActivity activity,Fragment fragment) {

        activity.getFragmentManager().beginTransaction().show(fragment).commit();
    }

    public static void hideFragment(@NonNull BaseActivity activity,Fragment fragment) {

        activity.getFragmentManager().beginTransaction().hide(fragment).commit();
    }

    public static boolean isActive(BaseActivity activity) {
        return activity != null && !activity.isFinishing() && !activity.isDestroyed();
    }

}
