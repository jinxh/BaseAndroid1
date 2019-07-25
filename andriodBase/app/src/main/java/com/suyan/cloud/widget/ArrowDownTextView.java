package com.suyan.cloud.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * Created by bianyin on 2019/5/6.
 */
public class ArrowDownTextView extends AppCompatTextView {
    private int displayHeight;
    private @DrawableRes
    int arrowDrawableResId;
    private Drawable arrowDrawable;
    private int arrowDrawableTint;

    public ArrowDownTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ArrowDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ArrowDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        Resources resources = getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, com.bianyin.setpview.R.styleable.NiceSpinner);
        arrowDrawableResId = typedArray.getResourceId(com.bianyin.setpview.R.styleable.NiceSpinner_arrowDrawable, com.bianyin.setpview.R.drawable.nicespinner_arrow);
        int defaultPadding = resources.getDimensionPixelSize(com.bianyin.setpview.R.dimen.one_and_a_half_grid_unit);

        setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        setPadding(resources.getDimensionPixelSize(com.bianyin.setpview.R.dimen.three_grid_unit), defaultPadding, defaultPadding,
                defaultPadding);
        setClickable(true);

        measureDisplayHeight();
    }

    private void measureDisplayHeight() {
        displayHeight = getContext().getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        arrowDrawable = initArrowDrawable(arrowDrawableTint);
        setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDrawable, null);
    }

    private Drawable initArrowDrawable(int drawableTint) {

        Drawable drawable = ContextCompat.getDrawable(getContext(), arrowDrawableResId);
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable);
            if (drawableTint != Integer.MAX_VALUE && drawableTint != 0) {
                DrawableCompat.setTint(drawable, drawableTint);
            }
        }
        return drawable;
    }

}
