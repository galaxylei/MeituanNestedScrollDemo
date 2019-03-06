package com.example.ruofan.meituan.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.example.ruofan.iconclickdemo.R;

/**
 * Created by ruofan on 2019/2/26.
 */
public class NestScrollingLayout extends LinearLayout implements NestedScrollingParent2 {

    private RecyclerView recyclerView;
    private View inner_scroll_view;

    private View mTop;

    private View mTop2;

    private View mTop3;

    private float mTopHeight;


    private float mNavHeight;

    private OverScroller mScroller;

    private Context context;

    private float mTouchSlop;

    private float maxVelocity;

    private float minVelocity;

    private float mLastY;

    private boolean isDragging;

    private VelocityTracker velocityTracker;

    private NestedScrollingParentHelper nestedScrollingParentHelper;

    public NestScrollingLayout(Context context) {
        super(context);

        initView(context);
    }


    public NestScrollingLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public NestScrollingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    private void initView(Context context) {
        mScroller = new OverScroller(context);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        maxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();

        minVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();

        nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerView = findViewById(R.id.meituan_recyclerView);
        mTop = findViewById(R.id.linearlayout_head);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopHeight = mTop.getMeasuredHeight();
    }


    public void fling(int velocity) {
        mScroller.fling(0, getScrollY(), 0, velocity, 0, 0, 0, (int) mTopHeight);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopHeight) {
            y = (int) mTopHeight;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }

    }


    @Override
    public void computeScroll() {
        //设置如何滚动，scroller的start方法只是设置了从何处开始以及到何处结束，
        //mScroller.getCurrY() 获得当前滚动位置
        if (mScroller.computeScrollOffset()) {
            //开始真正的滚动
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view1, int i) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view1, int i) {
        nestedScrollingParentHelper.onNestedScrollAccepted(view, view1, i);
    }

    @Override
    public void onStopNestedScroll(@NonNull View view) {

    }

    @Override
    public void onNestedScroll(@NonNull View view, int i, int i1, int i2, int i3) {

    }



    @Override
    public void onNestedPreScroll(@NonNull View view, int dx, int dy, @NonNull int[] consumed) {
        boolean hiddenTop = dy > 0 && getScrollY() < mTopHeight;  //是否隐藏head
        boolean showTop = dy < 0 && getScrollY() >= 0 && !view.canScrollVertically(-1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedFling(@NonNull View view, float x, final float y, boolean b) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {

        return false;
    }


    private boolean isNeedFling(float velocityY) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager my_layout_manager = (LinearLayoutManager) layoutManager;
            int firstVisiblePosition = my_layout_manager.findFirstCompletelyVisibleItemPosition();
            //
            if (firstVisiblePosition == 0 && my_layout_manager.findViewByPosition(0).getTop() == mNavHeight && recyclerView.canScrollVertically(1)) {
                return true;
            }

        }
        return false;

    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }


    @Override
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view1, int i, int i1) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view1, int i, int i1) {
        nestedScrollingParentHelper.onNestedScrollAccepted(view,view1,i,i1);
    }

    @Override
    public void onStopNestedScroll(@NonNull View view, int i) {

    }

    @Override
    public void onNestedScroll(@NonNull View view, int i, int i1, int i2, int i3, int i4) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View view, int dx, int dy, @NonNull int[] consumed, int type) {
        boolean hiddenTop = dy > 0 && getScrollY() < mTopHeight;  //是否隐藏head
        boolean showTop = dy < 0 && getScrollY() >= 0 && !view.canScrollVertically(-1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
//        consumed[1]=dy;   //这样父view就消费了所有的滑动
    }

}
