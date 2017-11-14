package cn.hotapk.fastandrutils.utils;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import cn.hotapk.fastandrutils.bean.SqlResponse;

/**
 * @author laijian
 * @version 2017/11/13
 * @Copyright (C)下午2:39 , www.hotapk.cn
 */
public class SqlService {


    public static String index(String filename) {
        String sqlindex = FAssetsARawUtils.getAssetsToString(filename);
        return sqlindex;

    }

    public static String getDbList() {
        Gson gson = new Gson();
        SqlResponse sqlResponse = new SqlResponse();
        List<String> rows = Arrays.asList(FUtils.getAppContext().databaseList());
        sqlResponse.setRows(rows);
        String getDbList = gson.toJson(sqlResponse);
        return getDbList;
    }

    public static String getTableList(String database) {
        SqlResponse sqlResponse = new SqlResponse();
        sqlResponse.setRows(FDatabaseUtils.getAllTableName(database));
        sqlResponse.setDbVersion(FDatabaseUtils.getDataBaseVersion(FDatabaseUtils.getDataBase(database)));
        return new Gson().toJson(sqlResponse);
    }

    public static String getTableDataList(String database, String tableName) {
        SqlResponse sqlResponse = FDatabaseUtils.getAllTableData(FDatabaseUtils.getDataBase(database), tableName);
        sqlResponse.setSuccessful(true);
        return new Gson().toJson(sqlResponse);
    }
}
