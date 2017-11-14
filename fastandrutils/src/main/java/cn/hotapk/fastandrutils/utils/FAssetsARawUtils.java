package cn.hotapk.fastandrutils.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)上午11:18 , www.hotapk.cn
 * assets raw 相关操作类
 */
public final class FAssetsARawUtils {
    private FAssetsARawUtils() {

    }

    /**
     * 拷贝assets下的文件的方法
     *
     * @param filePath   sd文件路径
     * @param assetsName
     * @throws IOException
     */
    public static boolean assetsDataToSD(String filePath, String assetsName) throws IOException {
        InputStream myInput;
        myInput = FUtils.getAppContext().getAssets().open(assetsName);
        return FFileUtils.inputStreamToFile(myInput, filePath);
    }


    /**
     * 读取assets文件内容
     *
     * @param assetsName
     * @return
     */
    public static String getAssetsToString(String assetsName) {
        try {
            return FFileUtils.readInp(FUtils.getAppContext().getAssets().open(assetsName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取assets文件转InputStream
     *
     * @param assetsName
     * @return
     */
    public static InputStream getAssetsToInp(String assetsName) {
        try {
            return FUtils.getAppContext().getAssets().open(assetsName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取assets文件 转byte数组
     *
     * @param assetsName
     * @return
     */
    public static byte[] readAssetsByteArray(String assetsName) {
        InputStream inputStream = null;

        try {
            inputStream = FUtils.getAppContext().getAssets().open(assetsName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream != null ? FFileUtils.inputStreamToByteArray(inputStream) : null;
    }


    /**
     * 读取raw文件内容
     *
     * @param rawId
     * @return
     */
    public static String getRawToString(int rawId) {
        return FFileUtils.readInp(FUtils.getAppContext().getResources().openRawResource(rawId));
    }


    /**
     * 读取raw文件 转byte数组
     *
     * @param rawId
     * @return
     */
    public static byte[] readRawFileToByteArray(int rawId) {
        InputStream inputStream = null;
        inputStream = FUtils.getAppContext().getResources().openRawResource(rawId);
        return inputStream != null ? FFileUtils.inputStreamToByteArray(inputStream) : null;
    }


    /**
     * 复制raw文件到sd卡
     *
     * @param rawId
     * @param outPutFileAbs
     */
    public static boolean copyRawFileToSdcard(String outPutFileAbs, int rawId) {
        InputStream inputStream = null;
        inputStream = FUtils.getAppContext().getResources().openRawResource(rawId);
        if (inputStream != null) {
            return FFileUtils.inputStreamToFile(inputStream, outPutFileAbs);
        }
        return false;
    }


}
