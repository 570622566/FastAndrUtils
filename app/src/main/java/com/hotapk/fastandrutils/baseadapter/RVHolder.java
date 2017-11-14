package com.hotapk.fastandrutils.baseadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RVHolder extends RecyclerView.ViewHolder {

    private RecViewHolder viewHolder;

    public RVHolder(View itemView) {
        super(itemView);
        viewHolder = RecViewHolder.getViewHolder(itemView);
    }


    public RecViewHolder getViewHolder() {
        return viewHolder;
    }

}
