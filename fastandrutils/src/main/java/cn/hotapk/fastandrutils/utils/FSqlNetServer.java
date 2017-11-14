package cn.hotapk.fastandrutils.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.hotapk.fastandrutils.bean.SqlResponse;

/**
 * @author laijian
 * @version 2017/11/13
 * @Copyright (C)上午10:17 , www.hotapk.cn
 * sql内网查询服务
 */
public class FSqlNetServer extends NanoHTTPD {

    public FSqlNetServer(int port) {
        super(port);
    }

    public FSqlNetServer(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String file_name = session.getUri().substring(1);
        Map<String, String> parms = session.getParms();
        StringBuffer br = new StringBuffer();
        if (parms.size() == 0) {
            if (file_name.endsWith("getDbList")) {
                br.append(SqlService.getDbList());
            } else {
                if (TextUtils.isEmpty(file_name)) {
                    file_name = "sqlindex.html";
                }
                br.append(SqlService.index(file_name));
            }
        } else {
            if (file_name.endsWith("getTableList")) {
                String database = parms.get("database");
                br.append(SqlService.getTableList(database));
            } else if (file_name.endsWith("getAllDataFromTheTable")) {
                String dbname = parms.get("dbname");
                String tableName = parms.get("tableName");
                br.append(SqlService.getTableDataList(dbname, tableName));

            }
        }
        return newFixedLengthResponse(Response.Status.OK, detectMimeType(file_name), br.toString());
    }

    public static String detectMimeType(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return null;
        } else if (fileName.endsWith(".html")) {
            return "text/html";
        } else if (fileName.endsWith(".js")) {
            return "application/javascript";
        } else if (fileName.endsWith(".css")) {
            return "text/css";
        } else {
            return "application/octet-stream";
        }
    }
}
