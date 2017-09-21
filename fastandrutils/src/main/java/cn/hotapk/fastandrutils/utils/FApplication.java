package cn.hotapk.fastandrutils.utils;

import android.app.Application;

/**
 * @author laijian
 * @version 2017/9/18
 * @Copyright (C)下午5:43 , www.hotapk.cn
 * Application
 */
public class FApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FUtils.init(this);
    }
}
