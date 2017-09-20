package cn.hotapk.fastandrlibs.utils;

import android.os.Environment;
import android.text.TextUtils;

/**
 * @author laijian
 * @version 2017/9/19
 * @Copyright (C)下午2:54 , www.hotapk.cn
 * 数据库导出
 */
public final class FDBExprotUtils {
    private FDBExprotUtils() {
    }

    /**
     * 开始导出数据 此操作比较耗时,建议在线程中进行
     *
     * @param targetFile   目标文件
     * @param databaseName 要拷贝的数据库文件名
     * @return 是否倒出成功
     */
    public boolean startExportDatabase(String targetFile,
                                       String databaseName) {

        if (!FLogUtils.getInstance().isDebug()) {
            return false;
        }
        String sourceFilePath = Environment.getDataDirectory() + "/data/"
                + FUtils.getAppContext().getPackageName() + "/databases/" + databaseName;
        String destFilePath = Environment.getExternalStorageDirectory()
                + (TextUtils.isEmpty(targetFile) ? (FUtils.getAppContext().getPackageName() + ".db")
                : targetFile);
        boolean isCopySuccess = FFileUtils
                .copyFileTo(sourceFilePath, destFilePath);
        if (isCopySuccess) {
            FLogUtils.getInstance().e("拷贝数据库成功 -> : "
                    + destFilePath);
        }
        return isCopySuccess;
    }

}
