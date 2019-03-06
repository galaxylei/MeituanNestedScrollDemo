package com.example.ruofan.meituan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ruofan on 2019/2/19.
 */
public class MeituanItemDecoration extends RecyclerView.ItemDecoration {

    private int height = 5;
    Paint mPaint;
    private GroupListener listener;

    public MeituanItemDecoration(GroupListener context) {
        this.listener = context;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int getLeft = parent.getLeft();
        int getRight = parent.getRight();
        int getChildCount = parent.getChildCount();
        for (int i = 0; i < getChildCount; i++) {
            View childView = parent.getChildAt(i);
            int bottom = childView.getBottom();
            int top = bottom - height;
//            c.drawRect(getLeft, top, getRight, bottom, mPaint);
        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getLeft() + parent.getPaddingLeft();
        int right = parent.getRight() - parent.getPaddingRight();
        for (int i = 0; i < itemCount; i++) {
            View firstView = parent.getChildAt(i);
            int childAdapterPosition = parent.getChildAdapterPosition(firstView);
            View top_view = listener.getGroupView(1);
            if (top_view == null) {
                return;
            }
            if (childAdapterPosition > 0) {
                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, top_view.getHeight());
                top_view.setDrawingCacheEnabled(true);
                top_view.layout(0, 0, right, top_view.getHeight());
                top_view.buildDrawingCache();
                Bitmap bitmap = top_view.getDrawingCache();
                c.drawBitmap(bitmap, 0, 150, mPaint);
//            c.drawRect(0, 0, right, firstView.getHeight(), mPaint);
            }
        }


    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //何时显示悬浮栏
        int position = parent.getChildAdapterPosition(view);
        if (position != 0) {
            outRect.top = 140;
        }
    }
}
