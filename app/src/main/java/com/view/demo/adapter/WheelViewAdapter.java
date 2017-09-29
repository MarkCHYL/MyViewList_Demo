package com.view.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.view.demo.utils.WheelUtils;
import com.view.demo.widget.WheelView;

/**
 *
 * Created by Mark on 2017/9/29.
 */

public class WheelViewAdapter extends RecyclerView.Adapter<WheelViewAdapter.WheelViewHolder> {

    /**
     * recyclerview 布局方向
     */
    final int orientation;
    /**
     * 每个item大小
     */
    public final int itemSize;
    /**
     * wheelview 滑动时上或下的空白数量
     */
    public final int itemCount;
    /**
     * wheelview 滑动时上或下空白总数量
     */
    private final int totalItemCount;

    public WheelView.WheelAdapter adapter;

    public WheelViewAdapter(int orientation, int itemSize, int itemCount) {
        this.orientation = orientation;
        this.itemSize = itemSize;
        this.itemCount = itemCount;
        this.totalItemCount = itemCount * 2;
    }

    @Override
    public void onBindViewHolder(WheelViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return totalItemCount + (adapter == null ? 0 : adapter.getItemCount());
    }

    @Override
    public WheelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new View(parent.getContext());
        view.setLayoutParams(WheelUtils.createLayoutParams(orientation, itemSize));
        return new WheelViewHolder(view);
    }

    public String getItemString(int index) {
        return adapter == null ? "" : adapter.getItem(index);
    }


    class WheelViewHolder extends RecyclerView.ViewHolder {

       public WheelViewHolder(View itemView) {
            super(itemView);
        }

    }

}
