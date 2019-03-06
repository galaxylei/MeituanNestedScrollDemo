package com.example.ruofan.meituan.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ruofan.iconclickdemo.R;
import com.example.ruofan.meituan.adapter.HotelConfigAdapter;
import com.example.ruofan.meituan.bean.HotelConfig;

import java.util.List;


/**
 * Created by ruofan on 2019/2/14.
 */
public class MeituanViewHolder extends RecyclerView.ViewHolder {

    RecyclerView recyclerView;
    private List<HotelConfig> list;
    private HotelConfigAdapter hotelConfigAdapter;

    public MeituanViewHolder(View itemView, List<HotelConfig> list) {
        super(itemView);
        this.list = list;

        recyclerView = itemView.findViewById(R.id.slide_recycler);
        hotelConfigAdapter = new HotelConfigAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(hotelConfigAdapter);
    }


    public void bindDatas(List<HotelConfig> list) {
        this.list = list;
    }

    public HotelConfigAdapter getHotelConfigAdapter() {
        return hotelConfigAdapter;
    }

}
