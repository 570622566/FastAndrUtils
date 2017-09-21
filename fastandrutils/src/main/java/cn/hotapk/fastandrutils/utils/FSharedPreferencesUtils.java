package cn.hotapk.fastandrutils.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)下午2:32 , www.hotapk.cn
 * SharedPreferences 工具类
 */
public final class FSharedPreferencesUtils {
    private FSharedPreferencesUtils() {

    }

    private static String name = "hotapk.cn";

    public static boolean putInt(String key, int value) {
        return putInt(name, key, value);
    }

    public static boolean putBoolean(String key, boolean value) {
        return putBoolean(name, key, value);
    }

    public static boolean putFloat(String key, float value) {
        return putFloat(name, key, value);
    }

    public static boolean putLong(String key, long value) {
        return putLong(name, key, value);
    }

    public static boolean putString(String key, String value) {
        return putString(name, key, value);
    }

    public static boolean putStringSet(String key, Set<String> value) {
        return putStringSet(name, key, value);
    }

    public static boolean putInt(String name, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(name).edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static boolean putBoolean(String name, String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences(name).edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean putFloat(String name, String key, float value) {
        SharedPreferences.Editor editor = getSharedPreferences(name).edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static boolean putString(String name, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(name).edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean putLong(String name, String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences(name).edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static boolean putStringSet(String name, String key, Set<String> value) {
        SharedPreferences.Editor editor = getSharedPreferences(name).edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }


    public static int getInt(String name, String key) {
        return getInt(name, key, 0);
    }

    public static boolean getBoolean(String name, String key) {
        return getBoolean(name, key, false);
    }

    public static float getFloat(String name, String key) {
        return getFloat(name, key, 0);
    }


    public static long getLong(String name, String key) {
        return getLong(name, key, 0);
    }

    public static String getString(String name, String key) {
        return getString(name, key, null);
    }


    public static Set<String> getStringSet(String name, String key) {
        return getStringSet(name, key, null);
    }

    public static int getInt(String name, String key, int defValue) {
        return getSharedPreferences(name).getInt(key, defValue);
    }

    public static boolean getBoolean(String name, String key, boolean defValue) {
        return getSharedPreferences(name).getBoolean(key, defValue);
    }

    public static float getFloat(String name, String key, float defValue) {
        return getSharedPreferences(name).getFloat(key, defValue);
    }

    public static long getLong(String name, String key, long defValue) {
        return getSharedPreferences(name).getLong(key, defValue);
    }

    public static String getString(String name, String key, String defValue) {
        return getSharedPreferences(name).getString(key, defValue);
    }

    public static Set<String> getStringSet(String name, String key, Set<String> defValue) {
        return getSharedPreferences(name).getStringSet(key, defValue);
    }

    public static SharedPreferences getSharedPreferences(String name) {
        return FUtils.getAppContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
    }

    /**
     * 清除数据
     *
     * @return
     */
    public static boolean clear() {
        return clear(name);
    }

    /**
     * 清除数据
     *
     * @param name
     * @return
     */
    public static boolean clear(String name) {
        SharedPreferences.Editor editor = getSharedPreferences(name).edit().clear();
        return editor.commit();
    }

    /**
     * 清除key中的数据
     *
     * @param key
     * @return
     */
    public static boolean clearByKey(String key) {
        return clearByKey(name, key);
    }

    /**
     * 清除key中的数据
     *
     * @param name
     * @param key
     * @return
     */
    public static boolean clearByKey(String name, String key) {
        SharedPreferences.Editor editor = getSharedPreferences(name).edit().remove(key);
        return editor.commit();
    }


}
