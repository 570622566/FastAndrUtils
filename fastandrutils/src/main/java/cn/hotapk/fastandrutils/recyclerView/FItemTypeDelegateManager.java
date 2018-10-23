package cn.hotapk.fastandrutils.recyclerView;

import android.support.v4.util.SparseArrayCompat;

/**
 * @author laijian
 * @version 2018/7/4
 * @Copyright (C)下午3:47 , www.hotapk.cn
 * @since
 */
public class FItemTypeDelegateManager<T> {


    SparseArrayCompat<FItemTypeDelegate<T>> delegates = new SparseArrayCompat();

    public FItemTypeDelegateManager addDelegate(FItemTypeDelegate<T> delegate) {

        if (delegates.get(delegate.getViewType()) != null) {

            throw new IllegalArgumentException(
                    "already registered for the viewType = "
                            + delegate.getViewType());
        }
        delegates.put(delegate.getViewType(), delegate);
        return this;
    }

    public int getViewResouce(int viewType) {

        if (delegates.get(viewType) == null) {
            throw new IllegalArgumentException("no this viewType = " + viewType);
        }
        return delegates.get(viewType).getViewResouce();

    }

    public int getSpanSize(int viewType) {

        if (delegates.get(viewType) == null) {
            throw new IllegalArgumentException("no this viewType = " + viewType);
        }
        return delegates.get(viewType).getSpanSize();

    }

    public int getItemViewType(T item, int postion) {

        int delegatesCount = delegates.size();

        for (int i = 0; i < delegatesCount; i++) {
            FItemTypeDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, postion)) {
                return delegate.getViewType();
            }
        }
        throw new IllegalArgumentException(
                "No FItemTypeDelegate added ");

    }

    public void onBindViewHolder(FViewHolder holder, T item, int position, int viewType) {

        if (delegates.get(viewType) == null) {
            throw new IllegalArgumentException("no this viewType = " + viewType);
        }

        delegates.get(viewType).convert(holder, item, position);


    }
}
