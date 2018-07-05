package cn.hotapk.fastandrutils.recyclerView;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author laijian
 * @version 2018/7/4
 * @Copyright (C)下午9:01 , www.hotapk.cn
 * @since
 */
public abstract class FSimpleRvAdapter<T> extends FBaseRvAdapter<T> {

    public FSimpleRvAdapter(Context context, List<T> datas, final @LayoutRes int viewRes) {
        super(context, datas);
        fItemTypeDelegateManager.addDelegate(new FItemTypeDelegate<T>() {
            @Override
            public int getViewType() {
                return 1;
            }

            @Override
            public boolean isForViewType(T data, int position) {
                return data != null;
            }

            @Override
            public int getViewResouce() {
                return viewRes;
            }

            @Override
            public int getSpanSize() {
                return 1;
            }

            @Override
            public void convert(FViewHolder holder, T item, int position) {
                convertHolder(holder, item, position);
            }
        });
    }

    public abstract void convertHolder(FViewHolder holder, T item, int position);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (checkRealData(position)) {
                        return gridManager.getSpanCount();
                    }
                    int type = getItemViewType(position);
                    return fItemTypeDelegateManager.getSpanSize(type);
                }
            });
        }

    }

    @Override
    public void onViewAttachedToWindow(FViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isStaggeredGridLayout(holder)) {
            handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
        }
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        if (checkRealData(position)) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            p.setFullSpan(true);
        }
    }

}
