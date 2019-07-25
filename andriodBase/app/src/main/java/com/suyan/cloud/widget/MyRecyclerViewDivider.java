package com.suyan.cloud.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.suyan.cloud.R;

public class MyRecyclerViewDivider extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mOrientation;
    /**
     *  系统默认的分割线
     * */
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    /**
     *  设置默认的分割线
     *  @param orientation 指代分割线的方向
     * */
    public MyRecyclerViewDivider(Context context, int orientation) {
        mOrientation = orientation;
        this.mDividerHeight = 2;
        this.mPaint = new Paint(1);
        this.mPaint.setColor(com.suyan.cloud.AppContext.s_instance.getResources().getColor(R.color.divider_color));
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     *  设置图片资源为分割线
     * */
    public MyRecyclerViewDivider(Context context, int orientation, int drawableId) {
//        this(context, orientation);
        mOrientation = orientation;
        this.mDivider = ContextCompat.getDrawable(context, drawableId);
        this.mDividerHeight = this.mDivider.getIntrinsicHeight();
    }

    /**
     *  设置线条颜色和高度给分割线
     * */
    public MyRecyclerViewDivider(Context context, int orientation, int dividerHeight, int dividerColor) {
//        this(context, orientation);
        mOrientation = orientation;
        this.mDividerHeight = dividerHeight;
        this.mPaint = new Paint(1);
        this.mPaint.setColor(dividerColor);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, this.mDividerHeight);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(this.mOrientation == LinearLayoutManager.VERTICAL) {
            this.drawVertical(c, parent);
        } else {
            this.drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize - 1; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize - 1; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}
