package cn.hotapk.fastandrutils.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by amitshekhar on 15/11/16.
 */

public class ResponseData {

    List<FieldInfor> allTablefield = new ArrayList<>();
    List<Map<String, Object>> datas = new ArrayList<>();
    private List<String> rows = new ArrayList<>();
    private boolean isSuccessful;
    private String error;
    private int dbVersion;

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }


    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(int dbVersion) {
        this.dbVersion = dbVersion;
    }

    public List<FieldInfor> getAllTablefield() {
        return allTablefield;
    }

    public void setAllTablefield(List<FieldInfor> allTablefield) {
        this.allTablefield = allTablefield;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }
}
