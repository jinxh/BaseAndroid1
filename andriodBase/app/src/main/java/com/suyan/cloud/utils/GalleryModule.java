package com.suyan.cloud.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;


@GlideModule
public final class GalleryModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        builder.setLogLevel(Log.ERROR);
    }
}
