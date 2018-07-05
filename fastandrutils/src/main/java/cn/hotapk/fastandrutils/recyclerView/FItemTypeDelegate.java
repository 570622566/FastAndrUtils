package cn.hotapk.fastandrutils.recyclerView;

/**
 * @author laijian
 * @version 2018/7/4
 * @Copyright (C)下午3:44 , www.hotapk.cn
 * @since
 */
public interface FItemTypeDelegate<T> {


    int getViewType();

    boolean isForViewType(T data, int position);

    int getViewResouce();

    int getSpanSize();

    void convert(FViewHolder holder, T t, int position);
}
