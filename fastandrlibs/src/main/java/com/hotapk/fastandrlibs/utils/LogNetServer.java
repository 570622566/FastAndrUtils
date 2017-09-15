package com.hotapk.fastandrlibs.utils;

import android.content.Context;

import java.io.File;
import java.util.Map;

/**
 * @author laijian
 * @version 2017/9/15
 * @Copyright (C)下午4:00 , www.hotapk.cn
 */
public class LogNetServer extends NanoHTTPD {
    private Context context;

    public LogNetServer(int port) {
        super(port);
    }

    public LogNetServer(String hostname, int port) {
        super(hostname, port);
    }

    public LogNetServer(int port, Context context) {
        super(port);
        this.context = context;
    }


    @Override
    public Response serve(IHTTPSession session) {
        String file_name = session.getUri().substring(1);
        StringBuffer br = new StringBuffer();
        String header = FAssetsARawUtils.getAssetsToString("baidu.html", context);
        br.append(header);
        String filedir = FLogUtils.getInstance().getLogFileDir();
        FLogUtils.getInstance().e("测试下效果-----");
        if (file_name.isEmpty()) {
            File[] files = FFileUtils.getFiles(filedir);
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    br.append(
                            "<div class=\"exp\"><a href = "
                                    + "/log?logfile="
                                    + files[i].getName() +
                                    " target=\"_blank\" >" + files[i].getName() +
                                    "</a ></div>"
                    );
                }

            }
        } else {
            Map<String, String> parms = session.getParms();
            if (parms.containsKey("logfile")) {
                String name = parms.get("logfile");
                String logstr = FFileUtils.readFile(filedir + "/" + name);
                br.append(logstr);
            } else {
                br.append("有问题");
            }

        }
        br.append("</body></html>");

        return newFixedLengthResponse(Response.Status.OK, "text/html", br.toString());
    }
}
