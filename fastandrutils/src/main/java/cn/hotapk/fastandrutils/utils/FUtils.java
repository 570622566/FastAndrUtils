package cn.hotapk.fastandrutils.utils;

import android.app.Application;

/**
 * @author laijian
 * @version 2017/9/18
 * @Copyright (C)下午4:56 , www.hotapk.cn
 */
public class FUtils {

    private volatile static FUtils fUtils;
    private Application application;

    private FUtils(Application application) {
        this.application = application;
        FActivityLifecycleCallbacks.init(this.application);
        FCrashUtils.getInstance();

    }

    public static void init(Application application) {
        if (fUtils == null) {
            synchronized (FUtils.class) {
                if (fUtils == null) {
                    fUtils = new FUtils(application);
                }
            }
        }
    }

    public static Application getAppContext() {
        if (fUtils != null) return fUtils.application;
        throw new NullPointerException("To initialize first");
    }

}
