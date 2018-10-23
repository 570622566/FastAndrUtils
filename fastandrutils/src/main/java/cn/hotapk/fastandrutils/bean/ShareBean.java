package cn.hotapk.fastandrutils.bean;

import android.graphics.drawable.Drawable;

/**
 * @author laijian
 * @version 2018/10/19
 * @Copyright (C)6:03 PM , www.hotapk.cn
 */
public class ShareBean {

    private String appName = "";
    private String packageName = "";
    private String className = "";
    private Drawable icon;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
