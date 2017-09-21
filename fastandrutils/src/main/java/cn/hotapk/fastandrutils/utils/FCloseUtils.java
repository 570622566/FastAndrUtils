package cn.hotapk.fastandrutils.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author laijian
 * @version 2017/9/13
 * @Copyright (C)上午12:09 , www.hotapk.cn
 * 关闭流
 */
public final class FCloseUtils {

    private FCloseUtils() {
    }

    /**
     * 关闭IO
     *
     * @param closeables closeables
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 安静关闭IO
     *
     * @param closeables closeables
     */
    public static void closeIOQuietly(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
