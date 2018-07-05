package cn.hotapk.fastandrutils.recyclerView;

import android.support.annotation.LayoutRes;

/**
 * @author laijian
 * @version 2018/7/5
 * @Copyright (C)上午9:20 , www.hotapk.cn
 * @since
 */
public abstract class FHeaderTypeDelegate<T> implements FItemTypeDelegate<T> {

    private int viewType;
    private int viewRes;

    public FHeaderTypeDelegate(int viewType, @LayoutRes int viewRes) {
        this.viewType = viewType;
        this.viewRes = viewRes;
    }

    @Override
    public int getViewType() {
        return (viewType + 1) * 1000;
    }

    @Override
    public boolean isForViewType(T data, int position) {
        return position == viewType;
    }

    @Override
    public int getViewResouce() {
        return viewRes;
    }

    @Override
    public int getSpanSize() {
        return 0;
    }

    @Override
    public void convert(FViewHolder holder, T t, int position) {
        headerConvert(holder, position);
    }

    abstract void headerConvert(FViewHolder holder, int position);

}
