package com.example.ruofan.meituan.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.ruofan.meituan.OnMyScrollChangeListener;
import com.example.ruofan.meituan.adapter.HotelConfigAdapter;
import com.example.ruofan.meituan.adapter.MeituanAdapter;

/**
 * Created by ruofan on 2019/2/18.
 */
public class MyScrollRecyclerView extends RecyclerView {


    public MyScrollRecyclerView(Context context) {
        super(context);
    }

    public MyScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (dy > 0) {   // 下滑
            if (getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
                int position = linearLayoutManager.findFirstVisibleItemPosition();
                if (position == 0) {
                    if (getAdapter() instanceof MeituanAdapter) {
                        MeituanAdapter myAdapter = (MeituanAdapter) getAdapter();
                        HotelConfigAdapter innerAdapter = (HotelConfigAdapter) myAdapter.getInnerAdapter();
                        innerAdapter.setTagId(-1);
                        innerAdapter.notifyDataSetChanged();
                    }
                }
            }
        } else { //上滑
            if (getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
                int position = linearLayoutManager.findFirstVisibleItemPosition();
                if (position == 0) {
                    if (getAdapter() instanceof MeituanAdapter) {
                        MeituanAdapter myAdapter = (MeituanAdapter) getAdapter();
                        HotelConfigAdapter innerAdapter = (HotelConfigAdapter) myAdapter.getInnerAdapter();
                        innerAdapter.setTagId(innerAdapter.getLastTagId());
                        innerAdapter.notifyDataSetChanged();
                    }
                }
            }
        }

    }

}
