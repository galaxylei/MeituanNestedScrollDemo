package com.example.ruofan.meituan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruofan.iconclickdemo.R;
import com.example.ruofan.meituan.bean.HotelConfig;
import com.example.ruofan.meituan.bean.MeituanConfig;
import com.example.ruofan.meituan.bean.NormalConfig;
import com.example.ruofan.meituan.holder.MeituanViewHolder;
import com.example.ruofan.meituan.holder.ShaixuanViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruofan on 2019/2/14.
 */
public class MeituanAdapter extends RecyclerView.Adapter {

    private static final int SLIDE_POSITION = 00000;
    private static final int NORMAL_POSITION = 11111;

    private static final int SHAIXUAN_POSTION = 222222;


    private MeituanConfig meituanConfig;

    private List<HotelConfig> list;

    private List<NormalConfig> normal_list;

    private Context context;

    private RecyclerView.Adapter innerAdapter;

    public MeituanAdapter(Context context) {
        meituanConfig = new MeituanConfig();
        this.context = context;
        init();
        meituanConfig.setList(list);
        meituanConfig.setNormal_list(normal_list);

    }

    private void init() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new HotelConfig("饭店的名字" + i, "", context.getResources().getDrawable(R.drawable.icon_play)));
        }
        normal_list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            normal_list.add(new NormalConfig("text" + i));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = null;
        switch (viewType) {
            case SLIDE_POSITION:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_layout, parent, false);
                holder = new MeituanViewHolder(view, meituanConfig.getList());
                break;
            case NORMAL_POSITION:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.normal_layout, parent, false);
                holder = new NormalViewHolder(view);
                break;
//            case  SHAIXUAN_POSTION:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shaixuan_layout, parent, false);
//                holder = new ShaixuanViewHolder(view);
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MeituanViewHolder) {
            MeituanViewHolder my_holder = (MeituanViewHolder) holder;
            bindHeadData(my_holder);
            innerAdapter = my_holder.getHotelConfigAdapter();

        } else if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            bindNormalData(normalViewHolder, meituanConfig.getNormal_list().get(position - 1));
        }
//        else if (holder instanceof ShaixuanViewHolder) {
//            ShaixuanViewHolder shaixuanViewHolder = (ShaixuanViewHolder) holder;
//            shaixuanViewHolder.bindData();
//        }
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return this.innerAdapter;
    }


    private void bindNormalData(NormalViewHolder normalViewHolder, NormalConfig normal_text) {
        normalViewHolder.textView.setText(normal_text.getText());
    }

    private void bindHeadData(MeituanViewHolder header_holder) {
        header_holder.bindDatas(meituanConfig.getList());
    }

    @Override
    public int getItemCount() {
        return meituanConfig.getNormal_list().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SLIDE_POSITION;
        }
//        else if (position == 1) {
//            return SHAIXUAN_POSTION;
//        }
        else {
            return NORMAL_POSITION;
        }
//        return super.getItemViewType(position);
    }

    public void setMoveToTop(int y, int oldY) {
        if (y != oldY) {
            int deltaY = y - oldY;

        }
    }



    class NormalViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.normal_text);
        }
    }

}
