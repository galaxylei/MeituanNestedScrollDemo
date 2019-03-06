package com.example.ruofan.meituan.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;

import com.example.ruofan.iconclickdemo.R;

import java.util.logging.Handler;

/**
 * Created by ruofan on 2019/3/5.
 */
public class InnerNestScrollingLayout extends NestedScrollView {

    private NestedScrollingChildHelper childHelper;

    private RecyclerView recyclerView;

    private View mTop;

    private View mTop2;

    private View mTop3;

    private float mTopHeight;

    private float mTop2Height;

    private float mTop3Height;

    private float mNavHeight;

    private OverScroller mScroller;

    private Context context;

    private float mTouchSlop;

    private float maxVelocity;

    private float minVelocity;

    private float mLastY;

    private boolean isDragging;

    private final int[] mScrollConsumed = new int[2];
    private final int[] mOffSetOff = new int[2];

    private innerNestScrollChangeListener listener;

    public interface innerNestScrollChangeListener{
        void onInnerNestScrollChangeListener(int l, int t, int oldl, int oldt);
    }

    public InnerNestScrollingLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        childHelper = new NestedScrollingChildHelper(this);

        childHelper.setNestedScrollingEnabled(true);

        mScroller = new OverScroller(context);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        maxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();

        minVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();

//        nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    public InnerNestScrollingLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public InnerNestScrollingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        recyclerView = findViewById(R.id.meituan_recyclerView);
        mTop = findViewById(R.id.linearlayout_head);
//        mTop2 = findViewById(R.id.header3);
//        mTop3 = findViewById(R.id.header5);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTop2Height = mTop2.getMeasuredHeight();
        mTop3Height = mTop3.getMeasuredHeight();
//        mTop2Height = mTop2.getMeasuredHeight();

    }


    public void onNestedPreScroll(@NonNull View view, int dx, int dy, @NonNull int[] consumed, int i2) {
        boolean hiddenTop = dy > 0 && getScrollY() < mTop2Height;  //是否隐藏head
        boolean showTop = dy < 0 && getScrollY() >= 0 && !view.canScrollVertically(-1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_TOUCH);
//                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float nowY = ev.getY();
                float dy = nowY - mLastY;
                boolean hiddenTop = dy > 0 && getScrollY() < mTop2Height;  //是否隐藏head
                boolean showTop = dy < 0 && getScrollY() >= 0 && !recyclerView.canScrollVertically(-1);

                if (dispatchNestedPreScroll(0, (int) dy, mScrollConsumed, mOffSetOff, ViewCompat.TYPE_TOUCH)) {
                    dy -= this.mScrollConsumed[1];
                }
                if (hiddenTop || showTop) {
//                    mScroller.startScroll(0, (int)mLastY,0,(int)dy);
//                    startNestedScroll()
                    recyclerView.setNestedScrollingEnabled(true);

                    invalidate();
                } else {
                    recyclerView.setNestedScrollingEnabled(false);
                }

//                scrollBy(0, (int) dy);
//                if (hiddenTop || showTop) {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                } else {
//                    getParent().requestDisallowInterceptTouchEvent(false);
//
//                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastY = ev.getY();
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

    public innerNestScrollChangeListener getListener() {
        return listener;
    }

    public void setListener(innerNestScrollChangeListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            listener.onInnerNestScrollChangeListener(l,t,oldl,oldt);
        }
    }

    //    @Override
//    public boolean isNestedScrollingEnabled() {
//        return true;
//    }
//
//    @Override
//    public void setNestedScrollingEnabled(boolean enabled) {
//        childHelper.setNestedScrollingEnabled(enabled);
//    }
//
//    @Override
//    public boolean startNestedScroll(int i, int i1) {
//        return childHelper.startNestedScroll(i,i1);
//    }
//
//    @Override
//    public void stopNestedScroll(int i) {
//
//    }
//
//    @Override
//    public boolean hasNestedScrollingParent(int i) {
//        return childHelper.hasNestedScrollingParent(i);
//    }
//
//    @Override
//    public boolean dispatchNestedScroll(int i, int i1, int i2, int i3, @Nullable int[] ints, int i4) {
//        return childHelper.dispatchNestedScroll(i, i1, i2, i3, ints, i4);
//    }
//
//    @Override
//    public boolean dispatchNestedPreScroll(int i, int i1, @Nullable int[] ints, @Nullable int[] ints1, int i2) {
//        return childHelper.dispatchNestedPreScroll(i, i1, ints, ints1, i2);
//    }


}
