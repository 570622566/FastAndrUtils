package com.hotapk.fastandrlibs.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

/**
 * @author laijian
 * @version 2017/9/19
 * @Copyright (C)下午7:39 , www.hotapk.cn
 */
public class FActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private List<Activity> activities = new LinkedList<>();
    @SuppressLint("StaticFieldLeak")
    private volatile static FActivityLifecycleCallbacks fActivityLifecycleCallbacks;
    private static int sAnimationId = 0;
    private Application application;


    private FActivityLifecycleCallbacks(Application application) {
        this.application = application;
        this.application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        if (fActivityLifecycleCallbacks == null) {
            synchronized (FActivityLifecycleCallbacks.class) {
                if (fActivityLifecycleCallbacks == null) {
                    fActivityLifecycleCallbacks = new FActivityLifecycleCallbacks(application);
                }
            }
        }
    }

    public static FActivityLifecycleCallbacks getfActivityLifecycle() {
        if (fActivityLifecycleCallbacks == null) {
            new Throwable("must init FActivityLifecycleCallbacks");
        }
        return fActivityLifecycleCallbacks;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        removeActivity(activity);
    }

    /**
     * 添加Activity
     */
    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new LinkedList<>();
        }

        if (!activities.contains(activity)) {
            activities.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * 移除Activity
     */
    public void removeActivity(Activity activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
        }

        if (activities.size() == 0) {
            activities = null;
        }
    }


    /**
     * 销毁所有activity
     */
    public void removeAllActivities() {
        for (Activity activity : activities) {
            if (null != activity) {
                activity.finish();
                activity.overridePendingTransition(0, sAnimationId);
            }
        }
    }
}
