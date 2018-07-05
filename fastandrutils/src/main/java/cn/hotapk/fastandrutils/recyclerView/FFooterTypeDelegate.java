package cn.hotapk.fastandrutils.recyclerView;

import android.support.annotation.LayoutRes;

/**
 * @author laijian
 * @version 2018/7/5
 * @Copyright (C)上午9:20 , www.hotapk.cn
 * @since
 */
public abstract class FFooterTypeDelegate<T> implements FItemTypeDelegate<T> {

    private int viewType;
    private int viewRes;

    public FFooterTypeDelegate(int viewType, @LayoutRes int viewRes) {
        this.viewType = viewType;
        this.viewRes = viewRes;
    }

    @Override
    public int getViewType() {
        return (viewType - 10) * 1000;
    }

    @Override
    public boolean isForViewType(T data, int position) {
        return true;
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
        footerConvert(holder, position);
    }

    abstract void footerConvert(FViewHolder holder, int position);

}
