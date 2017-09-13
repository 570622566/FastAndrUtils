package com.hotapk.fastandrlibs.utils;

import android.content.Context;
import android.content.res.Resources;

import java.lang.reflect.Field;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)上午10:23 , www.hotapk.cn
 * 获取资源相关类
 */
public class FResourcesUtils {

    public static int getAnimResources(Context context, String animName) {
        Resources res = context.getResources();
        return res.getIdentifier(animName, "anim", context.getPackageName());
    }

    public static int getMipmapResources(Context context, String mipmapName) {
        Resources res = context.getResources();
        return res.getIdentifier(mipmapName, "mipmap", context.getPackageName());
    }

    public static int getIdResources(Context context, String idName) {
        Resources res = context.getResources();
        return res.getIdentifier(idName, "id", context.getPackageName());
    }

    public static int getDrawableResources(Context context, String drawableName) {
        Resources res = context.getResources();
        return res.getIdentifier(drawableName, "drawable", context.getPackageName());
    }

    public static int getColorResources(Context context, String colorName) {
        Resources res = context.getResources();
        return res.getIdentifier(colorName, "color", context.getPackageName());
    }

    public static int getStringResources(Context context, String stringName) {
        Resources res = context.getResources();
        return res.getIdentifier(stringName, "string", context.getPackageName());
    }

    public static int getLayoutResources(Context context, String LayoutName) {
        Resources res = context.getResources();
        return res.getIdentifier(LayoutName, "layout", context.getPackageName());
    }

    public static int getAttrResources(Context context, String attrName) {
        Resources res = context.getResources();
        return res.getIdentifier(attrName, "attr", context.getPackageName());
    }

    public static int getStyleResources(Context context, String attrName) {
        Resources res = context.getResources();
        return res.getIdentifier(attrName, "style", context.getPackageName());
    }

    /**
     * context.getResources().getIdentifier 无法获取到 styleable 的数据
     *
     * @param name
     * @return
     * @paramcontext
     */

    public static int getStyleable(Context context, String name) {

        return ((Integer) getResourceId(context, name, "styleable")).intValue();

    }

    /**
     * 获取 styleable 的 ID 号数组
     *
     * @param name
     * @return
     * @paramcontext
     */
    public static int[] getStyleableArray(Context context, String name) {

        return (int[]) getResourceId(context, name, "styleable");

    }

    /**
     * 对于 context.getResources().getIdentifier 无法获取的数据 , 或者数组
     * <p/>
     * 资源反射值
     *
     * @param name
     * @param type
     * @return
     * @paramcontext
     */

    private static Object getResourceId(Context context, String name, String type) {

        String className = context.getPackageName() + ".R";

        try {

            Class<?> cls = Class.forName(className);

            for (Class<?> childClass : cls.getClasses()) {

                String simple = childClass.getSimpleName();

                if (simple.equals(type)) {

                    for (Field field : childClass.getFields()) {

                        String fieldName = field.getName();
                        if (fieldName.equals(name)) {
                            System.out.println(fieldName);
                            return field.get(null);

                        }

                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
}
