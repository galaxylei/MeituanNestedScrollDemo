package com.example.ruofan.meituan.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruofan.iconclickdemo.R;
import com.example.ruofan.meituan.bean.HotelConfig;

import java.util.List;

/**
 * Created by ruofan on 2019/2/14.
 */
public class HotelConfigAdapter extends RecyclerView.Adapter {

    private List<HotelConfig> list = null;
    private int tagId = 0;
    private int lastTagId = 0;
    private HotelConfigViewHolder hotelConfigViewHolder;

    private static final int HOTEL_NAME = 111111;

    public HotelConfigAdapter(List<HotelConfig> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_layout, parent, false);
        hotelConfigViewHolder = new HotelConfigViewHolder(view);
        return hotelConfigViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HotelConfigViewHolder) {
            final HotelConfigViewHolder header_holder = (HotelConfigViewHolder) holder;
            header_holder.hotel_name_tv.setText(list.get(position).getHotel_name());
            header_holder.hotel_image_image.setImageDrawable(list.get(position).getHotel_drawable());
            if (position == tagId) {
                header_holder.layout_background.setBackground(header_holder.itemView.getContext().getResources().getDrawable(R.drawable.circle_drawable_yellow));
                header_holder.hotel_name_tv.setTextColor(Color.RED);
            } else {
                header_holder.hotel_name_tv.setTextColor(Color.BLACK);
                header_holder.layout_background.setBackground(header_holder.itemView.getContext().getResources().getDrawable(R.drawable.circle_drawable));

            }
            header_holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTagId(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void setTagId(int id) {
        this.tagId = id;
        if (id >= 0) {
            this.lastTagId = id;
        }
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public void setLastTagId() {
        this.lastTagId = tagId;
    }

    public int getLastTagId() {
        return lastTagId;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setMoveToTop(int deltaY) {

        hotelConfigViewHolder.itemView.setTranslationY(deltaY);

    }

    class HotelConfigViewHolder extends RecyclerView.ViewHolder {

        private TextView hotel_name_tv;
        private ImageView hotel_image_image;
        private FrameLayout layout_background;

        public HotelConfigViewHolder(View itemView) {
            super(itemView);
            layout_background = itemView.findViewById(R.id.layout_background);
            hotel_name_tv = itemView.findViewById(R.id.hotel_name);
            hotel_image_image = itemView.findViewById(R.id.hotel_image);
        }
    }


}
