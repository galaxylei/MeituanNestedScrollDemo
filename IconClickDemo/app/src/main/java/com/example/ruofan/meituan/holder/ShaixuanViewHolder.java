package com.example.ruofan.meituan.holder;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.ruofan.iconclickdemo.R;
import com.example.ruofan.meituan.view.AbstractMeituanShaixuanView;

/**
 * Created by ruofan on 2019/2/20.
 */
public class ShaixuanViewHolder implements View.OnClickListener {

    TextView total_order_tv;
    TextView sale_mount;
    TextView juli_tv;
    TextView shaixuan_tv;

    View background_view;

    private AbstractMeituanShaixuanView meituanShaixuanView;


    public ShaixuanViewHolder(ViewStub viewStub) {
        viewStub.setLayoutResource(R.layout.shaixuan_layout);
        View view = viewStub.inflate();

        total_order_tv = view.findViewById(R.id.total_order);
        sale_mount = view.findViewById(R.id.sale_mount);
        juli_tv = view.findViewById(R.id.juli);
        shaixuan_tv = view.findViewById(R.id.shaixuan);
        background_view = view.findViewById(R.id.sort_mask);


        if (view instanceof ViewGroup) {
            initViews((ViewGroup) view);
        }

        initListener();

    }

    private void initListener() {
        shaixuan_tv.setOnClickListener(this);
        background_view.setOnClickListener(this);
    }

    private void initViews(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child_view = viewGroup.getChildAt(i);
            if (child_view instanceof AbstractMeituanShaixuanView) {
                meituanShaixuanView = (AbstractMeituanShaixuanView) child_view;
                meituanShaixuanView.setVisibility(View.GONE);
                background_view.setVisibility(View.GONE);
            }
        }
    }

    public void bindData() {
        shaixuan_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.shaixuan:
                popupShaixuanWindow();
                break;
            case R.id.sort_mask:
                popupShaixuanWindow();
                break;

        }
    }

    private void popupShaixuanWindow() {
        if (meituanShaixuanView.getVisibility() == View.GONE) {
            meituanShaixuanView.setVisibility(View.VISIBLE);
            background_view.setVisibility(View.VISIBLE);
        } else {
            meituanShaixuanView.setVisibility(View.GONE);
            background_view.setVisibility(View.GONE);
        }
    }
}
