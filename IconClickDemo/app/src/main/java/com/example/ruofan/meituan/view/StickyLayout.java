package com.example.ruofan.meituan.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ruofan.iconclickdemo.R;
import com.example.ruofan.meituan.adapter.MeituanAdapter;

/**
 * Created by ruofan on 2019/3/6.
 */
public class StickyLayout extends FrameLayout {

    RecyclerView recyclerView;

    private Context mContext;

    public StickyLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public StickyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StickyLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void init(Context context) {
        mContext = context;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerView = findViewById(R.id.meituan_recyclerView);
        addOnScrollListener();
        addStickyLayout();
    }

    FrameLayout mStickyLayout;

    private void addStickyLayout() {
        mStickyLayout = new FrameLayout(mContext);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mStickyLayout.setLayoutParams(layoutParams);
        super.addView(mStickyLayout, 1, layoutParams);
    }

    private void addOnScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = linearLayoutManager.findFirstVisibleItemPosition();
                if (position > 5) {
                    updateStickyView();
                } else {
                    deleteStickyView();
                }
            }


        });
    }

    private void deleteStickyView() {

        mStickyLayout.setVisibility(GONE);
    }


    private void updateStickyView() {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof MeituanAdapter) {
            MeituanAdapter meituanAdapter = (MeituanAdapter) adapter;
            View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            mStickyLayout.addView(view);
            mStickyLayout.setVisibility(VISIBLE);
        }

        if (mStickyLayout.getChildCount() > 0 && mStickyLayout.getHeight() == 0) {
            mStickyLayout.requestLayout();
        }
    }
}
