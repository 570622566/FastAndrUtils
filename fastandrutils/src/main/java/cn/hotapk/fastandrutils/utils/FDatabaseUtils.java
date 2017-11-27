package cn.hotapk.fastandrutils.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hotapk.fastandrutils.bean.FieldInfor;
import cn.hotapk.fastandrutils.bean.ResponseData;

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
    public static List<FieldInfor> getAllTablefield(SQLiteDatabase database, String tableName) {
        List<FieldInfor> fields = new ArrayList<>();
        Cursor cursor = database.rawQuery("PRAGMA table_info([" + tableName + "])", null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                do {
                    FieldInfor fieldInfor = new FieldInfor();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        final String columnName = cursor.getColumnName(i);
                        switch (columnName) {
                            case "name":
                                fieldInfor.setTitle(cursor.getString(i));
                                break;
                            case "type":
                                fieldInfor.setType(cursor.getString(i));
                                break;
                            case "pk":
                                fieldInfor.setPrimary(cursor.getInt(i) == 1);
                                break;
                            default:
                        }

                    }
                    fields.add(fieldInfor);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        FLogUtils.getInstance().e(fields);

        return fields;
    }

    /**
     * 获取所有表数据
     *
     * @param database
     * @param tableName
     */
    public static ResponseData getAllTableData(SQLiteDatabase database, String tableName) {
        List<FieldInfor> allTablefield = getAllTablefield(database, tableName);
        List<Map<String, Object>> datas = new ArrayList<>();
        ResponseData responseData = new ResponseData();
        Cursor cursor = database.rawQuery("select * from " + tableName, null);
        getCursorData(cursor, allTablefield, datas);
        responseData.setAllTablefield(allTablefield);
        responseData.setDatas(datas);
        cursor.close();
        return responseData;
    }

    /**
     * 获取表数据
     *
     * @param database
     * @param tableName
     * @param pageSize
     * @param index
     */
    public static ResponseData getTableData(SQLiteDatabase database, String tableName, int pageSize, int index) {
        ResponseData responseData = new ResponseData();

        List<FieldInfor> allTablefield = getAllTablefield(database, tableName);
        List<Map<String, Object>> datas = new ArrayList<>();
        String sqlquery = "select * from " + tableName + " limit " + pageSize + " offset " + pageSize * (index - 1);
        Cursor cursor = database.rawQuery(sqlquery, null);
        getCursorData(cursor, allTablefield, datas);
        responseData.setAllTablefield(allTablefield);
        responseData.setDatas(datas);
        cursor.close();
        return responseData;
    }

    private static void getCursorData(Cursor cursor, List<FieldInfor> allTablefield, List<Map<String, Object>> datas) {
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < allTablefield.size(); i++) {
                    switch (allTablefield.get(i).getType()) {
                        case DataType.BLOB:
                            map.put(allTablefield.get(i).getTitle(), FConvertUtils.blobToString(cursor.getBlob(i)));
                            break;
                        case DataType.INTEGER:
                            map.put(allTablefield.get(i).getTitle(), cursor.getInt(i));
                        case DataType.REAL:
                            map.put(allTablefield.get(i).getTitle(), cursor.getDouble(i));
                        default:
                            map.put(allTablefield.get(i).getTitle(), cursor.getString(i));
                            break;
                    }
                }
                datas.add(map);
            } while (cursor.moveToNext());
        }

    }

    /**
     * 添加数据
     *
     * @param dbName    数据库名
     * @param tableName 数据库表
     * @param parms     key：value
     * @return
     */
    public static boolean addData(String dbName, String tableName, Map<String, String> parms) {
        SQLiteDatabase dataBase = null;

        try {
            dataBase = getDataBase(dbName);
            ContentValues cv = new ContentValues();
            putContentValues(cv, dataBase, tableName, parms);
            long res = dataBase.insert(tableName, null, cv);
            if (res == -1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (dataBase != null) {
                dataBase.close();
            }
        }

    }

    /**
     * 修改数据
     *
     * @param dbName    数据库名
     * @param tableName 数据库表
     * @param parms     key：value
     * @return
     */
    public static boolean editData(String dbName, String tableName, Map<String, String> parms) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase dataBase = null;
        try {
            dataBase = getDataBase(dbName);
            String[] pk_table = putContentValues(cv, dataBase, tableName, parms);
            long res = dataBase.update(tableName, cv, pk_table[0] + " = ?", new String[]{pk_table[1]});
            if (res == -1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (dataBase != null) {
                dataBase.close();
            }
        }

    }

    /**
     * 删除数据
     *
     * @param dbName    数据库名
     * @param tableName 数据库表
     * @param parms     key：value
     * @return
     */
    public static boolean delData(String dbName, String tableName, Map<String, String> parms) {
        SQLiteDatabase dataBase = null;
        try {
            dataBase = getDataBase(dbName);
            Set<String> keys = parms.keySet();
            String whereClause = "";
            String[] whereArgs = new String[1];
            for (String key : keys) {
                whereClause = key + " = ?";
                whereArgs[0] = parms.get(key);
            }
            long res = dataBase.delete(tableName, whereClause, whereArgs);
            if (res == -1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (dataBase != null) {
                dataBase.close();
            }
        }

    }


    private static String[] putContentValues(ContentValues cv, SQLiteDatabase dataBase, String tableName, Map<String, String> parms) throws Exception {

        String[] pk_table = new String[2];
        List<FieldInfor> allTablefield = getAllTablefield(dataBase, tableName);
        Map<String, FieldInfor> fieldmap = new HashMap<>();
        for (int i = 0; i < allTablefield.size(); i++) {
            fieldmap.put(allTablefield.get(i).getTitle(), allTablefield.get(i));
        }
        Set<String> keys = parms.keySet();
        for (String key : keys) {
            if (fieldmap.containsKey(key)) {
                if (fieldmap.get(key).isPrimary()) {
                    pk_table[0] = fieldmap.get(key).getTitle();
                    pk_table[1] = parms.get(key);
                }
                if (fieldmap.get(key).isPrimary() && fieldmap.get(key).getTitle().equalsIgnoreCase("id")) {
                    continue;
                }
                switch (fieldmap.get(key).getType()) {
                    case DataType.INTEGER:
                        if (parms.get(key).equals("true") || parms.get(key).equals("false")) {
                            cv.put(key, Boolean.valueOf(parms.get(key)));
                        } else {
                            cv.put(key, Integer.parseInt(parms.get(key)));
                        }
                        break;
                    case DataType.REAL:
                        cv.put(key, Double.parseDouble(parms.get(key)));
                        break;
                    case DataType.BLOB:
                        byte[] bytes = null;
                        try {
                            bytes = parms.get(key).getBytes("utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        cv.put(key, bytes);
                        break;
                    default:
                        cv.put(key, parms.get(key));
                        break;

                }
            }
        }
        return pk_table;
    }


}
