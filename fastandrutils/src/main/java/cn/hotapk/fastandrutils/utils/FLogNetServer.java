package cn.hotapk.fastandrutils.utils;

import java.io.File;
import java.util.Map;

/**
 * @author laijian
 * @version 2017/9/15
 * @Copyright (C)下午4:00 , www.hotapk.cn
 * log内网webservers
 */
public class FLogNetServer extends NanoHTTPD {

    public FLogNetServer(int port) {
        super(port);
    }

    public FLogNetServer(String hostname, int port) {
        super(hostname, port);
    }


    @Override
    public Response serve(IHTTPSession session) {
        String file_name = session.getUri().substring(1);
        StringBuffer br = new StringBuffer();
        String header = FAssetsARawUtils.getAssetsToString("index.html");
        br.append(header);
        String filedir = FLogUtils.getInstance().getLogFileDir();
        if (file_name.isEmpty()) {
            File[] files = FFileUtils.orderByDate(new File(filedir), true);
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
