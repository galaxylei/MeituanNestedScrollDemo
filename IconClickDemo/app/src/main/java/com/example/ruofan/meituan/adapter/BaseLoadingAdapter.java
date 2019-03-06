package com.example.ruofan.meituan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruofan.iconclickdemo.R;

/**
 * Created by ruofan on 2019/1/11.
 */
public abstract class BaseLoadingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int POSITION_HEADER = 0;
    private static final int POSITION_BOTTOM = 1;
    private Context context;

    private View headerLoading;

    public BaseLoadingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case POSITION_HEADER:
                holder = onBindHeader();
                break;
            case POSITION_BOTTOM:
                holder = onBindBottom();
                break;
            default:
                holder = onCreateHolder(parent, viewType);
                break;
        }

        return holder;
    }

    private RecyclerView.ViewHolder onBindBottom() {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom, null);
        return new BottomeViewHolder(view);
    }

    private RecyclerView.ViewHolder onBindHeader() {
        View view = LayoutInflater.from(context).inflate(R.layout.header, null);
        HeaderViewHolder holder = new HeaderViewHolder(view);
        headerLoading = view.findViewById(R.id.image_loading);
        holder.imageView = (ImageView) headerLoading;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case POSITION_HEADER:
                onBindLoadingHeader(holder);
                break;
            case POSITION_BOTTOM:
                onBindLoadingBottom(holder);
                break;
            default:
                bindHolder(holder, position);
                break;
        }
    }

    private void onBindLoadingBottom(RecyclerView.ViewHolder holder) {
        BottomeViewHolder bottomeViewHolder = null;
        if (holder instanceof BottomeViewHolder) {
            bottomeViewHolder = (BottomeViewHolder) holder;
            bottomeViewHolder.textView.setVisibility(View.VISIBLE);
        }
    }


    private void onBindLoadingHeader(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerViewHolder = null;
        if (holder instanceof HeaderViewHolder) {
            headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.startAnimation();
        }

    }

    public View getHeaderView() {

        return headerLoading;
    }

    public View getBottomView() {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom, null);

        return view;
    }


    public abstract void bindHolder(RecyclerView.ViewHolder holder, int position);

    public abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType);

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        boolean isLoading = false;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_loading);
        }

        public void startAnimation() {
            isLoading = true;
            if (imageView.getAnimation() == null) {
                Animation animation = AnimationUtils.loadAnimation(imageView.getContext(), R.anim.loading);
                imageView.startAnimation(animation);
            }
        }

        public void stopAnimation() {
            isLoading = false;
            if (imageView.getAnimation() != null) {
                imageView.getAnimation().cancel();
            }
        }

    }

    public static class BottomeViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public BottomeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.bottom);
        }

    }

}
