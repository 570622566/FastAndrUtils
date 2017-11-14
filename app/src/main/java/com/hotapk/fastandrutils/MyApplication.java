package com.hotapk.fastandrutils;

import android.app.Application;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import cn.hotapk.fastandrutils.utils.FUtils;

/**
 * @author laijian
 * @version 2017/11/10
 * @Copyright (C)下午5:31 , www.hotapk.cn
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FUtils.init(this);
        LitePal.initialize(this);
    }
}
