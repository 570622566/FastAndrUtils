package cn.hotapk.fastandrutils.utils;

import android.view.View;

/**
 * @author laijian
 * @version 2017/9/20
 * @Copyright (C)下午2:20 , www.hotapk.cn
 * 防止重复点击点击
 */
public class FClickProxy implements View.OnClickListener {

    private View.OnClickListener origin;
    private long lastclick = 0;
    private long timems = 1000; //ms
    private IAgain mIAgain;

    public FClickProxy(View.OnClickListener origin) {
        this(origin, 1000);
    }

    public FClickProxy(View.OnClickListener origin, long timems) {
        this(origin, null, timems);
    }


    public FClickProxy(View.OnClickListener origin, IAgain again, long timems) {
        this.origin = origin;
        this.mIAgain = again;
        this.timems = timems;
    }


    @Override
    public void onClick(View v) {
        if (System.currentTimeMillis() - lastclick >= timems) {
            origin.onClick(v);
            lastclick = System.currentTimeMillis();
        } else {
            if (mIAgain != null) mIAgain.onAgain();
        }
    }

    public interface IAgain {
        void onAgain();//重复点击
    }
}
