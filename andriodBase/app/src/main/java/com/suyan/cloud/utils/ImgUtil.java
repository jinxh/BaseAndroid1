package com.suyan.cloud.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.suyan.cloud.log.LogUtils;

import java.io.File;

public class ImgUtil {
    private static final String TAG = "ImgUtil";

    public static void loadImg(ImageView imageView, String url) {
        if (imageView.getContext() == null) {
            LogUtils.i(TAG, "loadImg context is null");
            return;
        }
        if (isGif(url)) {
            GlideApp.with(imageView.getContext())
                    .asGif()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(imageView);
        } else {
            GlideApp.with(imageView.getContext())
                    .load(url)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
        }
    }

    public static void loadImg(ImageView imageView, @DrawableRes int drawId) {
        if (imageView.getContext() == null) {
            LogUtils.i(TAG, "loadImg context is null");
            return;
        }
        GlideApp.with(imageView.getContext())
                .load(drawId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    public static void loadGif(ImageView imageView, @DrawableRes int drawId) {
        if (imageView.getContext() == null) {
            LogUtils.i(TAG, "loadImg context is null");
            return;
        }
        GlideApp.with(imageView.getContext())
                .asGif()
                .load(drawId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    public static void loadImg(ImageView imageView, String url, @DrawableRes int placeholderDrawId,
                               @DrawableRes int errorDrawId) {
        if (imageView.getContext() == null) {
            LogUtils.i(TAG, "loadImg context is null");
            return;
        }
        if (isGif(url)) {
//            url = "https://media.giphy.com/media/2gYeMIqQvE19u/giphy.gif";
            GlideApp.with(imageView.getContext())
                    .asGif()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(placeholderDrawId)
                    .error(errorDrawId)
                    .into(imageView);
        } else {
            GlideApp.with(imageView.getContext())
                    .load(url)
                    .dontAnimate()
                    .placeholder(placeholderDrawId)
                    .error(errorDrawId)
                    .into(imageView);
        }

    }

    public static void loadImg(ImageView imageView, String url, @DrawableRes int errorDrawId) {
        if (imageView.getContext() == null) {
            LogUtils.i(TAG, "loadImg context is null");
            return;
        }
        if (isGif(url)) {
            GlideApp.with(imageView.getContext())
                    .asGif()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .load(url)
                    .error(errorDrawId)
                    .into(imageView);
        } else {
            GlideApp.with(imageView.getContext())
                    .load(url)
                    .dontAnimate()
                    .error(errorDrawId)
                    .into(imageView);
        }


    }

    public static void loadImg(ImageView imageView, File file, @DrawableRes int errorDrawId) {
        GlideApp.with(imageView.getContext()).
                load(file)
                .dontAnimate()
                .error(errorDrawId)
                .into(imageView);
    }

    public static void pauseLoadImg(Context context) {
        if (context != null) {
            GlideApp.with(context).pauseRequests();
        }
    }

    public static void resumeLoadImg(Context context) {
        if (context != null) {
            GlideApp.with(context).resumeRequests();
        }
    }

    public static Boolean isGif(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            String[] strs = filename.split("\\.");
            return strs[strs.length - 1].equalsIgnoreCase("gif");
        }
        return false;
    }
}
