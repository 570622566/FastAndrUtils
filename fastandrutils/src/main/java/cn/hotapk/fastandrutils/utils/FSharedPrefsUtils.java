package cn.hotapk.fastandrutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)下午2:32 , www.hotapk.cn
 * SharedPreferences 工具类
 */
public final class FSharedPrefsUtils {
    private static String name = "hotapk.cn";

    private FSharedPrefsUtils() {

    }

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

    public static int getInt(String key) {
        return getInt(name, key, 0);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(name, key, false);
    }

    public static float getFloat(String key) {
        return getFloat(name, key, 0);
    }

    public static long getLong(String key) {
        return getLong(name, key, 0);
    }

    public static String getString(String key) {
        return getString(name, key, null);
    }

    public static Set<String> getStringSet(String key) {
        return getStringSet(name, key, null);
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


    /**
     * 获取SharedPreference xml数据
     *
     * @return
     */
    public static List<String> getSharedPreferenceXMl() {

        ArrayList<String> tags = new ArrayList<>();
        String rootPath = FUtils.getAppContext().getApplicationInfo().dataDir + "/shared_prefs";
        File root = new File(rootPath);
        if (root.exists()) {
            for (File file : root.listFiles()) {
                String fileName = file.getName();
                if (fileName.endsWith(".xml")) {
                    tags.add(fileName);
                }
            }
        }
        Collections.sort(tags);
        return tags;
    }

    /**
     * 获取pref的数据
     *
     * @param prefname
     * @return
     */
    public static List<Map<String, Object>> getPrefData(String prefname) {
        SharedPreferences preferences = FUtils.getAppContext().getSharedPreferences(prefname.replace(".xml", ""), Context.MODE_PRIVATE);
        Map<String, ?> entries = preferences.getAll();
        List<Map<String, Object>> datas = new ArrayList<>();
        for (Map.Entry<String, ?> entry : entries.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("Key", entry.getKey());
            map.put("Value", entry.getValue().toString());
            if (entry.getValue() instanceof Integer) {
                map.put("dataType", "integer");
            } else if (entry.getValue() instanceof Long) {
                map.put("dataType", "long");
            } else if (entry.getValue() instanceof Float) {
                map.put("dataType", "float");
            } else if (entry.getValue() instanceof Boolean) {
                map.put("dataType", "boolean");
            } else if (entry.getValue() instanceof Set) {
                map.put("dataType", "set");
            } else {
                map.put("dataType", "string");
            }
            datas.add(map);
        }
        return datas;
    }


}
