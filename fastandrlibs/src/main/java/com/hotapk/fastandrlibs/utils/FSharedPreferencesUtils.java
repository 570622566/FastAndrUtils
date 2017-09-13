package com.hotapk.fastandrlibs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)下午2:32 , www.hotapk.cn
 * SharedPreferences 工具类
 */
public class FSharedPreferencesUtils {

    private static String name = "hotapk.cn";

    public static boolean putInt(Context context, String key, int value) {
        return putInt(context, name, key, value);
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        return putBoolean(context, name, key, value);
    }

    public static boolean putFloat(Context context, String key, float value) {
        return putFloat(context, name, key, value);
    }

    public static boolean putLong(Context context, String key, long value) {
        return putLong(context, name, key, value);
    }

    public static boolean putString(Context context, String key, String value) {
        return putString(context, name, key, value);
    }

    public static boolean putStringSet(Context context, String key, Set<String> value) {
        return putStringSet(context, name, key, value);
    }

    public static boolean putInt(Context context, String name, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context, name).edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static boolean putBoolean(Context context, String name, String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences(context, name).edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean putFloat(Context context, String name, String key, float value) {
        SharedPreferences.Editor editor = getSharedPreferences(context, name).edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static boolean putString(Context context, String name, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context, name).edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean putLong(Context context, String name, String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences(context, name).edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static boolean putStringSet(Context context, String name, String key, Set<String> value) {
        SharedPreferences.Editor editor = getSharedPreferences(context, name).edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }


    public static int getInt(Context context, String name, String key) {
        return getInt(context, name, key, 0);
    }

    public static boolean getBoolean(Context context, String name, String key) {
        return getBoolean(context, name, key, false);
    }

    public static float getFloat(Context context, String name, String key) {
        return getFloat(context, name, key, 0);
    }


    public static long getLong(Context context, String name, String key) {
        return getLong(context, name, key, 0);
    }

    public static String getString(Context context, String name, String key) {
        return getString(context, name, key, null);
    }


    public static Set<String> getStringSet(Context context, String name, String key) {
        return getStringSet(context, name, key, null);
    }

    public static int getInt(Context context, String name, String key, int defValue) {
        return getSharedPreferences(context, name).getInt(key, defValue);
    }

    public static boolean getBoolean(Context context, String name, String key, boolean defValue) {
        return getSharedPreferences(context, name).getBoolean(key, defValue);
    }

    public static float getFloat(Context context, String name, String key, float defValue) {
        return getSharedPreferences(context, name).getFloat(key, defValue);
    }

    public static long getLong(Context context, String name, String key, long defValue) {
        return getSharedPreferences(context, name).getLong(key, defValue);
    }

    public static String getString(Context context, String name, String key, String defValue) {
        return getSharedPreferences(context, name).getString(key, defValue);
    }

    public static Set<String> getStringSet(Context context, String name, String key, Set<String> defValue) {
        return getSharedPreferences(context, name).getStringSet(key, defValue);
    }

    public static SharedPreferences getSharedPreferences(Context context, String name) {
        return context.getSharedPreferences(name, Activity.MODE_PRIVATE);
    }

    /**
     * 清除数据
     *
     * @param context
     * @return
     */
    public static boolean clear(Context context) {
        return clear(context, name);
    }

    /**
     * 清除数据
     *
     * @param context
     * @param name
     * @return
     */
    public static boolean clear(Context context, String name) {
        SharedPreferences.Editor editor = getSharedPreferences(context, name).edit().clear();
        return editor.commit();
    }

    /**
     * 清除key中的数据
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean clearByKey(Context context, String key) {
        return clearByKey(context, name, key);
    }

    /**
     * 清除key中的数据
     *
     * @param context
     * @param name
     * @param key
     * @return
     */
    public static boolean clearByKey(Context context, String name, String key) {
        SharedPreferences.Editor editor = getSharedPreferences(context, name).edit().remove(key);
        return editor.commit();
    }


}
