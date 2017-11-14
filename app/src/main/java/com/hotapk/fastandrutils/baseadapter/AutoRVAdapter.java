package com.hotapk.fastandrutils.baseadapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

public abstract class AutoRVAdapter<T> extends RecyclerView.Adapter<RVHolder> {


    public List<T> list;

    protected Context context;
    private int position;

    public int getPosition() {
        return position;
    }


    public AutoRVAdapter(Context context, List<T> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(viewType, parent, false);
        return new RVHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return setLayoutid(position);
    }

    public abstract int setLayoutid(int position);

    @Override
    public void onViewRecycled(final RVHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override
    public void onBindViewHolder(final RVHolder holder, final int position) {
        this.position = position;
        onBindViewHolder(holder.getViewHolder(), position, list.size() < getItemCount() ? null : list.get(position));
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(null, v, holder.getPosition(), holder.getItemId());
                }
            });
        }

    }

    public abstract void onBindViewHolder(RecViewHolder holder, int position, T item);

    @Override
    public int getItemCount() {
        return list.size();
    }

    private AdapterView.OnItemClickListener onItemClickListener;

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}