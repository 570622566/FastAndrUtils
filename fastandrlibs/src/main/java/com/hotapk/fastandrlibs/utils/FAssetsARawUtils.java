package com.hotapk.fastandrlibs.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)上午11:18 , www.hotapk.cn
 * assets raw 相关操作类
 */
public class FAssetsARawUtils {


    /**
     * 拷贝assets下的文件的方法
     *
     * @param filePath   sd文件路径
     * @param assetsName
     * @param context
     * @throws IOException
     */
    public static boolean assetsDataToSD(String filePath, String assetsName, Context context) throws IOException {
        InputStream myInput;
        myInput = context.getApplicationContext().getAssets().open(assetsName);
        return FFileUtils.inputStreamToFile(myInput, filePath);
    }


    /**
     * 读取assets文件内容
     *
     * @param assetsName
     * @param context
     * @return
     */
    public static String getAssetsToString(String assetsName, Context context) {
        try {
            return FFileUtils.readInp(context.getApplicationContext().getAssets().open(assetsName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 读取assets文件 转byte数组
     *
     * @param assetsName
     * @param context
     * @return
     */
    public static byte[] readAssetsByteArray(String assetsName, Context context) {
        InputStream inputStream = null;

        try {
            inputStream = context.getApplicationContext().getAssets().open(assetsName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream != null ? FFileUtils.inputStreamToByteArray(inputStream) : null;
    }


    /**
     * 读取raw文件内容
     *
     * @param rawId
     * @param context
     * @return
     */
    public static String getRawToString(int rawId, Context context) {
        return FFileUtils.readInp(context.getApplicationContext().getResources().openRawResource(rawId));
    }


    /**
     * 读取raw文件 转byte数组
     *
     * @param rawId
     * @param context
     * @return
     */
    public static byte[] readRawFileToByteArray(int rawId, Context context) {
        InputStream inputStream = null;

        inputStream = context.getApplicationContext().getResources().openRawResource(rawId);

        return inputStream != null ? FFileUtils.inputStreamToByteArray(inputStream) : null;
    }


    /**
     * 复制raw文件到sd卡
     *
     * @param rawId
     * @param context
     * @param outPutFileAbs
     */
    public static void copyRawFileToSdcard(int rawId, Context context, String outPutFileAbs) {
        InputStream inputStream = null;

        inputStream = context.getApplicationContext().getResources().openRawResource(rawId);
        if (inputStream != null) {
            FFileUtils.inputStreamToFile(inputStream, outPutFileAbs);
        }

    }


}
