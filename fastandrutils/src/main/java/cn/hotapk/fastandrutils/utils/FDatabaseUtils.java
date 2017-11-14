package cn.hotapk.fastandrutils.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hotapk.fastandrutils.bean.SqlFieldInfor;
import cn.hotapk.fastandrutils.bean.SqlResponse;

/**
 * @author laijian
 * @version 2017/9/19
 * @Copyright (C)下午2:54 , www.hotapk.cn
 * 数据库导出
 */
public final class FDatabaseUtils {
    private FDatabaseUtils() {
    }

    /**
     * 开始导出数据 此操作比较耗时,建议在线程中进行
     *
     * @param destFilePath 目标文件
     * @param databaseName 要拷贝的数据库文件名
     * @return 是否倒出成功
     */
    public static boolean exportDatabase(String destFilePath,
                                         String databaseName) {

        if (!FLogUtils.getInstance().isDebug()) {
            return false;
        }
        if (!destFilePath.endsWith(databaseName)) {
            File temppath = new File(destFilePath, databaseName);
            destFilePath = temppath.getAbsolutePath();
        }
        boolean isCopySuccess = FFileUtils
                .copyFileTo(FUtils.getAppContext().getDatabasePath(databaseName).getAbsolutePath(), destFilePath);
        return isCopySuccess;
    }

    /**
     * 获取数据库版本
     *
     * @param database
     * @return
     */
    public static int getDataBaseVersion(SQLiteDatabase database) {
        return database.getVersion();
    }

    /**
     * 根据数据库名称 获取 SQLiteDatabase
     *
     * @param databaseName 数据库名称
     * @return
     */
    public static SQLiteDatabase getDataBase(String databaseName) {
        return SQLiteDatabase.openOrCreateDatabase(FUtils.getAppContext().getDatabasePath(databaseName), null);
    }

    /**
     * 根据文件路径获取SQLiteDatabase
     *
     * @param databasepath 数据库路径
     * @return
     */
    public static SQLiteDatabase getDataBase(File databasepath) {
        return SQLiteDatabase.openOrCreateDatabase(databasepath, null);
    }

    /**
     * 获取数据库所有表名
     *
     * @param databaseName 数据库文件名
     * @return
     */
    public static List<String> getAllTableName(String databaseName) {
        databaseName = databaseName.endsWith(".db") ? databaseName : databaseName + ".db";
        return getAllTableName(getDataBase(databaseName));
    }

    /**
     * 获取数据库所有表名
     *
     * @param database SQLiteDatabase
     * @return
     */
    public static List<String> getAllTableName(SQLiteDatabase database) {
        List<String> tabs = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    tabs.add(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return tabs;
    }

    /**
     * 获取数据库表的所有字段
     *
     * @param database
     * @param tableName
     * @return
     */
    public static List<SqlFieldInfor> getAllTablefield(SQLiteDatabase database, String tableName) {
        List<SqlFieldInfor> fields = new ArrayList<>();
        Cursor cursor = database.rawQuery("PRAGMA table_info([" + tableName + "])", null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                do {
                    SqlFieldInfor sqlFieldInfor = new SqlFieldInfor();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        final String columnName = cursor.getColumnName(i);
                        switch (columnName) {
                            case "name":
                                sqlFieldInfor.setTitle(cursor.getString(i));
                                break;
                            case "type":
                                sqlFieldInfor.setType(cursor.getString(i));
                                break;
                            case "pk":
                                sqlFieldInfor.setPrimary(cursor.getInt(i) == 1);
                                break;
                            default:
                        }

                    }
                    fields.add(sqlFieldInfor);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return fields;
    }

    /**
     * 获取所有表数据
     *
     * @param database
     * @param tableName
     */
    public static SqlResponse getAllTableData(SQLiteDatabase database, String tableName) {
        List<SqlFieldInfor> allTablefield = getAllTablefield(database, tableName);
        List<Map<String, Object>> datas = new ArrayList<>();
        SqlResponse sqlResponse = new SqlResponse();
        Cursor cursor = database.rawQuery("select * from " + tableName, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < allTablefield.size(); i++) {
                    switch (allTablefield.get(i).getType()) {
                        case "blob":
                            map.put(allTablefield.get(i).getTitle(), FConvertUtils.blobToString(cursor.getBlob(i)));
                            break;
                        default:
                            map.put(allTablefield.get(i).getTitle(), cursor.getString(i));
                            break;
                    }
                }
                datas.add(map);
            } while (cursor.moveToNext());
        }
        FLogUtils.getInstance().e(datas);
        sqlResponse.setAllTablefield(allTablefield);
        sqlResponse.setDatas(datas);
        cursor.close();
        return sqlResponse;
    }

    /**
     * 获取表数据
     *
     * @param database
     * @param tableName
     * @param pageSize
     * @param index
     */
    public static void getTableData(SQLiteDatabase database, String tableName, int pageSize, int index) {
        List<SqlFieldInfor> allTablefield = getAllTablefield(database, tableName);
        List<Map<String, Object>> datas = new ArrayList<>();
        String sqlquery = "select * from " + tableName + " limit " + pageSize + " offset " + pageSize * (index - 1);
        Cursor cursor = database.rawQuery(sqlquery, null);
        getCursorData(cursor, allTablefield, datas);
        FLogUtils.getInstance().e(datas);
    }

    private static void getCursorData(Cursor cursor, List<SqlFieldInfor> allTablefield, List<Map<String, Object>> datas) {
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < allTablefield.size(); i++) {
                    switch (allTablefield.get(i).getType()) {
                        case "blob":
                            map.put(allTablefield.get(i).getTitle(), FConvertUtils.blobToString(cursor.getBlob(i)));
                            break;
                        default:
                            map.put(allTablefield.get(i).getTitle(), cursor.getString(i));
                            break;
                    }
                }
                datas.add(map);
            } while (cursor.moveToNext());
        }

    }

}
