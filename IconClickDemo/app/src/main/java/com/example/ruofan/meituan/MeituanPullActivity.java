package com.example.ruofan.meituan;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.ruofan.iconclickdemo.R;
import com.example.ruofan.meituan.adapter.HotelConfigAdapter;
import com.example.ruofan.meituan.adapter.MeituanAdapter;
import com.example.ruofan.meituan.bean.HotelConfig;
import com.example.ruofan.meituan.holder.ShaixuanViewHolder;
import com.example.ruofan.meituan.view.InnerNestScrollingLayout;
import com.example.ruofan.meituan.view.MyScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MeituanPullActivity extends FragmentActivity {

    private RecyclerView recyclerView;

    private MyScrollRecyclerView recyclerViewH;

    private MeituanAdapter adapter;

    private HotelConfigAdapter hotelConfigAdapter;

    private ViewStub viewStub;

    List<HotelConfig> list;

    private int mTouchSlop;

    private LinearLayout linearlayout_head;

    private LinearLayout inner_layout;

    private Scroller inner_scroller;

    private InnerNestScrollingLayout wrapper_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meituan_pull);

        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        initView();
        initData();
        initRecycler();
        initChangeScrollListener();

    }

    private void initChangeScrollListener() {


    }

    private void initData() {

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new HotelConfig("饭店的名字" + i, "", getResources().getDrawable(R.drawable.icon_play)));
        }
    }

    private void initRecycler() {
        adapter = new MeituanAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        hotelConfigAdapter = new HotelConfigAdapter(list);


        recyclerViewH.setAdapter(hotelConfigAdapter);
        recyclerViewH.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }


    private void initView() {
        recyclerView = findViewById(R.id.meituan_recyclerView);
        viewStub = findViewById(R.id.shaixuan_container);
        new ShaixuanViewHolder(viewStub);
        recyclerViewH = findViewById(R.id.meituan_recyclerView_h);
        linearlayout_head = findViewById(R.id.linearlayout_head);
        inner_scroller = new Scroller(this);

    }


}
