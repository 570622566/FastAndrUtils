package cn.hotapk.fastandrutils.utils;

import android.content.res.Resources;

import java.lang.reflect.Field;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)上午10:23 , www.hotapk.cn
 * 获取资源相关类
 */
public class FResourcesUtils {

    public static int getAnimResources(String animName) {
        Resources res = FUtils.getAppContext().getResources();
        return res.getIdentifier(animName, "anim", FUtils.getAppContext().getPackageName());
    }

    public static int getMipmapResources(String mipmapName) {
        Resources res = FUtils.getAppContext().getResources();
        return res.getIdentifier(mipmapName, "mipmap", FUtils.getAppContext().getPackageName());
    }

    public static int getIdResources(String idName) {
        Resources res = FUtils.getAppContext().getResources();
        return res.getIdentifier(idName, "id", FUtils.getAppContext().getPackageName());
    }

    public static int getDrawableResources(String drawableName) {
        Resources res = FUtils.getAppContext().getResources();
        return res.getIdentifier(drawableName, "drawable", FUtils.getAppContext().getPackageName());
    }

    public static int getColorResources(String colorName) {
        Resources res = FUtils.getAppContext().getResources();
        return res.getIdentifier(colorName, "color", FUtils.getAppContext().getPackageName());
    }

    public static int getStringResources(String stringName) {
        Resources res = FUtils.getAppContext().getResources();
        return res.getIdentifier(stringName, "string", FUtils.getAppContext().getPackageName());
    }

    public static int getLayoutResources(String LayoutName) {
        Resources res = FUtils.getAppContext().getResources();
        return res.getIdentifier(LayoutName, "layout", FUtils.getAppContext().getPackageName());
    }

    public static int getAttrResources(String attrName) {
        Resources res = FUtils.getAppContext().getResources();
        return res.getIdentifier(attrName, "attr", FUtils.getAppContext().getPackageName());
    }

    public static int getStyleResources(String attrName) {
        Resources res = FUtils.getAppContext().getResources();
        return res.getIdentifier(attrName, "style", FUtils.getAppContext().getPackageName());
    }

    /**
     * context.getResources().getIdentifier 无法获取到 styleable 的数据
     *
     * @param name
     * @return
     */

    public static int getStyleable(String name) {

        return ((Integer) getResourceId(name, "styleable")).intValue();

    }


    /**
     * 获取 styleable 的 ID 号数组
     *
     * @param name
     * @return
     */
    public static int[] getStyleableArray(String name) {

        return (int[]) getResourceId(name, "styleable");

    }

    /**
     * 对于 context.getResources().getIdentifier 无法获取的数据 , 或者数组资源反射值
     *
     * @param name
     * @param type
     * @return
     */
    private static Object getResourceId(String name, String type) {

        String className = FUtils.getAppContext().getPackageName() + ".R";

        try {

            Class<?> cls = Class.forName(className);

            for (Class<?> childClass : cls.getClasses()) {

                String simple = childClass.getSimpleName();

                if (simple.equals(type)) {

                    for (Field field : childClass.getFields()) {

                        String fieldName = field.getName();
                        if (fieldName.equals(name)) {
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
