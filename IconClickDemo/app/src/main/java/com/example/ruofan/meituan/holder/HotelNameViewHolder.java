package com.example.ruofan.meituan.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ruofan.iconclickdemo.R;
import com.example.ruofan.meituan.bean.NormalConfig;

/**
 * Created by ruofan on 2019/3/8.
 */
public class HotelNameViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView hotel_name_rv;

    private 

    public HotelNameViewHolder(View itemView) {
        super(itemView);
        hotel_name_rv = itemView.findViewById(R.id.hotel_name_rv);




    }

    public void bindHotelName(NormalConfig normalConfig) {

    }
}
