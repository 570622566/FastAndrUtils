/*
 *
 *  *    Copyright (C) 2016 Amit Shekhar
 *  *    Copyright (C) 2011 Android Open Source Project
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package cn.hotapk.fastandrutils.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by amitshekhar on 15/11/16.
 */

public class SqlResponse {

    private List<String> rows = new ArrayList<>();
    private List<String> columns = new ArrayList<>();
    List<SqlFieldInfor> allTablefield =new ArrayList<>();
    List<Map<String, Object>> datas = new ArrayList<>();

    private boolean isSuccessful;
    private String error;
    private int dbVersion;

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
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

    public List<SqlFieldInfor> getAllTablefield() {
        return allTablefield;
    }

    public void setAllTablefield(List<SqlFieldInfor> allTablefield) {
        this.allTablefield = allTablefield;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }
}
