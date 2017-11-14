package cn.hotapk.fastandrutils.utils;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laijian
 * @version 2017/10/24
 * @Copyright (C)上午10:46 , www.hotapk.cn
 * 获取AndroidManifest.xml的部分数据
 */
public class FManifestUtils {

    /**
     * 获取manifest有注册的权限
     *
     * @return
     */
    public static String[] getRegPermission() {
        PackageInfo appInfo = null;
        List<String> perls = new ArrayList<>();
        try {
            appInfo = FUtils.getAppContext().getPackageManager()
                    .getPackageInfo(FAppUtils.getAppPackageName(),
                            PackageManager.GET_PERMISSIONS);
            String[] perms = appInfo.requestedPermissions;
            for (String perm : perms) {
                perls.add(perm);
            }
            return perls.toArray(new String[perls.size()]);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 ApplicationInfo
     *
     * @return
     */
    public static ApplicationInfo getApplicationInfo() {
        try {
            ApplicationInfo appInfo = FUtils.getAppContext()
                    .getPackageManager()
                    .getApplicationInfo(FAppUtils.getAppPackageName(), PackageManager.GET_META_DATA);
            return appInfo;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }

    }

    /**
     * 获取application应用<meta-data>元素
     */
    public static String getAppMetaData(String name) {
        try {
            String value = getApplicationInfo().metaData.getString(name);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取application应用<meta-data>元素的对应的资源id值
     */
    public static int getAppMetaDataResourceId(String name) {
        try {
            int value = getApplicationInfo().metaData.getInt(name);
            return value;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取 ActivityInfo
     */

    public static ActivityInfo getActivityInfo(Class<?> cls) {
        try {
            ComponentName cn = new ComponentName(FUtils.getAppContext(), cls);
            ActivityInfo activityInfo = FUtils.getAppContext().getPackageManager().getActivityInfo(cn,
                    PackageManager.GET_META_DATA);
            return activityInfo;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /**
     * 获取activity应用<meta-data>元素
     */
    public static String getActivityMetaData(String name, Class<?> cls) {
        try {
            String value = getActivityInfo(cls).metaData.getString(name);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取activity应用<meta-data>元素的对应的资源id值
     */
    public static int getActivityMetaDataResourceId(String name, Class<?> cls) {
        try {
            int value = getActivityInfo(cls).metaData.getInt(name);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取receiver应用<meta-data>元素
     */
    public static String getReceiverMetaData(String name, Class<?> cls) {
        try {
            String value = getActivityInfo(cls).metaData.getString(name);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取receiver应用<meta-data>元素的对应的资源id值
     */
    public static int getReceiverMetaDataResourceId(String name, Class<?> cls) {
        try {
            int value = getActivityInfo(cls).metaData.getInt(name);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * 获取ServiceInfo
     */
    public static ServiceInfo getServiceInfo(Class<?> cls) {
        ComponentName cn = new ComponentName(FUtils.getAppContext(), cls);
        ServiceInfo info = null;
        try {
            info = FUtils.getAppContext().getPackageManager()
                    .getServiceInfo(cn, PackageManager.GET_META_DATA);
            return info;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取service应用<meta-data>元素
     */
    public static String getServiceMetaData(String name, Class<?> cls) {
        try {
            String value = getServiceInfo(cls).metaData.getString(name);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取service应用<meta-data>元素的对应的资源id值
     */
    public static int getServiceMetaDataResourceId(String name, Class<?> cls) {
        try {
            int value = getServiceInfo(cls).metaData.getInt(name);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
