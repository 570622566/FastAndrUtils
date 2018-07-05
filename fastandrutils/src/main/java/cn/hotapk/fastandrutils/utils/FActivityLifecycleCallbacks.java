package cn.hotapk.fastandrutils.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

/**
 * @author laijian
 * @version 2017/9/19
 * @Copyright (C)下午7:39 , www.hotapk.cn
 * activity生命周期
 */
public class FActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @SuppressLint("StaticFieldLeak")
    private volatile static FActivityLifecycleCallbacks fActivityLifecycleCallbacks;
    private static int sAnimationId = 0;
    private Stack<Activity> activities;
    private Application application;
    private LifecycleListener lifecycleListener;

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

    public void setLifecycleListener(LifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
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
        if (lifecycleListener != null) {
            lifecycleListener.onActivityResumed(activity);
        }

    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (lifecycleListener != null) {
            lifecycleListener.onActivityPaused(activity);
        }
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
            activities = new Stack<>();
        }
        if (!activities.contains(activity)) {
            activities.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activities.lastElement();
        return activity;
    }

    /**
     * 获取前一个activity
     *
     * @return
     */
    public Activity beforeActivity() {
        Activity activity = activities.elementAt(activities.size() - 2);
        return activity;
    }

    /**
     * 移除Activity
     */
    public void removeActivity(Activity activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
            activity.finish();
        }

        if (activities.size() == 0) {
            activities = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activities) {
            if (activity.getClass().equals(cls)) {
                removeActivity(activity);
            }
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
        activities.clear();
    }

    public interface LifecycleListener {
        void onActivityResumed(Activity activity);

        void onActivityPaused(Activity activity);
    }
}
