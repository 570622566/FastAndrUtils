package cn.hotapk.fastandrutils.utils;

import android.os.Environment;

import java.io.File;

/**
 * @author laijian
 * @version 2017/9/20
 * @Copyright (C)下午3:36 , www.hotapk.cn
 * <p>
 * 清理app缓存
 */
public final class FCleanUtils {

    private FCleanUtils() {
    }

    /**
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     */
    public static void cleanInternalCache() {
        deleteFilesByDirectory(FUtils.getAppContext().getCacheDir());
    }

    /**
     * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
     */
    public static void cleanDatabases() {
        deleteFilesByDirectory(new File(FUtils.getAppContext().getFilesDir().getPath()
                + FUtils.getAppContext().getPackageName() + "/databases"));
    }

    /**
     * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     */
    public static void cleanSharedPreference() {
        deleteFilesByDirectory(new File(FUtils.getAppContext().getFilesDir().getPath()
                + FUtils.getAppContext().getPackageName() + "/shared_prefs"));
    }

    /**
     * 按名字清除本应用数据库
     *
     * @param dbName 数据库名称
     */
    public static void cleanDatabaseByName(String dbName) {
        FUtils.getAppContext().deleteDatabase(dbName);
    }

    /**
     * 清除/data/data/com.xxx.xxx/files下的内容
     */
    public static void cleanFiles() {
        deleteFilesByDirectory(FUtils.getAppContext().getFilesDir());
    }

    /**
     * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     */
    public static void cleanExternalCache() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(FUtils.getAppContext().getExternalCacheDir());
        }
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     *
     * @param filePath 文件路径
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * 清除本应用所有的数据
     *
     * @param filePath 文件路径
     */
    public static void cleanApplicationData(String... filePath) {
        cleanInternalCache();
        cleanExternalCache();
        cleanDatabases();
        cleanSharedPreference();
        cleanFiles();
        for (String fp : filePath) {
            cleanCustomCache(fp);
        }
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件。 此操作较危险，请慎用；
     *
     * @param directory 文件夹File对象
     */
    private static void deleteFilesByDirectory(File directory) {
        FFileUtils.delDir(directory.getAbsolutePath());
    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static String getCacheSize(File file) throws Exception {
        return FConvertUtils.binaryFormatSize(getFolderSize(file));
    }


}
