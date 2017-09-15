package com.hotapk.fastandrlibs.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)下午4:35 , www.hotapk.cn
 * 日志操作类
 */
public class FLogUtils {
    private String TOP_LINE = "" +
            "\n^^^^^^^^^^^^^less code,less bug^^^^^^^^^^^^^^\n" +
            "                   _ooOoo_\n" +
            "                  o8888888o\n" +
            "                  88\" . \"88\n" +
            "                  (| -_- |)\n" +
            "                  O\\  =  /O\n" +
            "               ____/`---'\\____\n" +
            "             .'  \\\\|     |//  `.\n" +
            "            /  \\\\|||  :  |||//  \\\n" +
            "           /  _||||| -:- |||||-  \\\n" +
            "           |   | \\\\\\  -  /// |   |\n" +
            "           | \\_|  ''\\---/''  |   |\n" +
            "           \\  .-\\__  `-`  ___/-. /\n" +
            "         ___`. .'  /--.--\\  `. . __\n" +
            "      .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
            "     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
            "     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
            "======`-.____`-.___\\_____/___.-`____.-'======\n" +
            "                   `=---='\n" +
            "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
            "            佛祖保佑       永无BUG\n" +
            "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n";
    private String TOP_BORDER = "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════";
    private String LEFT_BORDER = "║ ";
    private String BOTTOM_BORDER = "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════";
    private boolean debug = true;//是否打印log
    private boolean savesd = false;//是否存log到sd卡
    private int CHUNK_SIZE = 106; //设置字节数
    private String logDir = "";//设置文件存储目录
    private long logSize = 1 * 1024 * 1024L;//设置log文件大小 k
    private ExecutorService execu = Executors.newFixedThreadPool(1);

    private volatile static FLogUtils fLogUtils;

    private LogNetServer testHttpd;

    public static FLogUtils getInstance() {
        if (fLogUtils == null) {
            synchronized (FLogUtils.class) {
                if (fLogUtils == null) {
                    fLogUtils = new FLogUtils();
                }
            }
        }
        return fLogUtils;
    }

    private FLogUtils() {
        Log.e("www.hotapk.cn_log", TOP_LINE);
        initLogFile();
    }

    private void initLogFile() {
        logDir = FFileUtils.getRootDir() + "/FastAndrUtils_log";
        FFileUtils.mkDir(logDir);
    }

    /**
     * 启动log的WebServer服务
     *
     * @param port
     * @param context
     */
    public void startLogServer(int port, Context context) {
        if (testHttpd == null) {
            synchronized (FLogUtils.class) {
                if (testHttpd == null) {
                    testHttpd = new LogNetServer(port, context.getApplicationContext());
                }
            }
        }
        try {
            testHttpd.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭log的WebServer服务
     */
    public void stopLogServer() {
        if (testHttpd != null && testHttpd.isAlive()) {
            testHttpd.stop();
        }
    }

    public void v(String msg) {
        v("www.hotapk.cn", msg);
    }

    public void d(String msg) {
        d("www.hotapk.cn", msg);
    }

    public void i(String msg) {
        i("www.hotapk.cn", msg);
    }

    public void w(String msg) {
        w("www.hotapk.cn", msg);
    }

    public void e(String msg) {
        e("www.hotapk.cn", msg);
    }

    public void v(String tag, String msg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.v(tag, msgFormat(stackstr, msg));
        }
        saveToSd(stackstr, msg);
    }

    public void d(String tag, String msg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.d(tag, msgFormat(stackstr, msg));
        }
        saveToSd(stackstr, msg);
    }

    public void i(String tag, String msg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.i(tag, msgFormat(stackstr, msg));
        }
        saveToSd(stackstr, msg);
    }

    public void w(String tag, String msg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.w(tag, msgFormat(stackstr, msg));
        }
        saveToSd(stackstr, msg);
    }


    public void e(String tag, String msg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.e(tag, msgFormat(stackstr, msg));
        }
        saveToSd(stackstr, msg);

    }

    /**
     * 是否开启bebug模式
     *
     * @param debug
     * @return
     */
    public FLogUtils debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * 是否保存到sd卡
     *
     * @param savesd
     * @return
     */
    public FLogUtils saveSD(boolean savesd) {
        this.savesd = savesd;
        return this;
    }

    /**
     * 设置log文件大小
     *
     * @param logSize
     * @return
     */
    public FLogUtils setLogSize(int logSize) {
        this.logSize = logSize;
        return this;
    }

    /**
     * 设置log文件目录
     *
     * @param logDir
     * @return
     */
    public FLogUtils setlogDir(String logDir) {
        if (!logDir.isEmpty()) {
            this.logDir = logDir;
        }
        return this;
    }

    public String getLogFileDir() {
        return logDir;
    }

    private String targetStackTraceMSg() {
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        if (targetStackTraceElement != null) {
            return "at " + targetStackTraceElement.getClassName() + "." + targetStackTraceElement.getMethodName() +
                    "(" + targetStackTraceElement.getFileName() + ":" + targetStackTraceElement.getLineNumber() + ")";

        } else {
            return "";
        }
    }

    private StackTraceElement getTargetStackTraceElement() {
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(FLogUtils.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

    private String msgFormat(String stackstr, String msg) {
        byte[] bytes = new byte[0];
        try {
            bytes = msg.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int length = bytes.length;
        String newMsg = TOP_BORDER + "\n" + LEFT_BORDER + "\t" + FTimeUtils.dateToString(new Date()) + "\n" + LEFT_BORDER + "\t" + stackstr;
        if (length > CHUNK_SIZE) {
            int i = 0;
            while (i < length) {
                int count = Math.min(length - i, CHUNK_SIZE);
                String tempStr = new String(bytes, i, count);
                newMsg += "\n" + LEFT_BORDER + "\t" + tempStr;
                i += CHUNK_SIZE;
            }
        } else {
            newMsg += "\n" + LEFT_BORDER + "\t" + msg;
        }
        newMsg += "\n" + BOTTOM_BORDER;
        return newMsg;

    }

    private void saveToSd(final String stackstr, final String msg) {
        if (!savesd) {
            return;
        }

        execu.submit(new Runnable() {
            @Override
            public void run() {
                String data = FTimeUtils.dateToString(new Date(), "yyyy-MM-dd");
                File[] files = FFileUtils.orderByDate(new File(logDir), true);
                List<File> filels = FFileUtils.filter(files, data);
                String filepath;
                if (filels.size() > 0) {
                    Long length = FFileUtils.getLeng(filels.get(0));
                    if (length > logSize) {
                        int index = Integer.parseInt(filels.get(0).getName().replace("log_" + data + "_", "").replace(".html", ""));
                        int id = index + 1;
                        filepath = logDir + "/" + "log_" + data + "_" + id + ".html";
                        FFileUtils.creatFile(filepath);
                    } else {
                        filepath = filels.get(0).getAbsolutePath();
                    }

                } else {
                    filepath = logDir + "/" + "log_" + data + "_1.html";
                    FFileUtils.creatFile(filepath);
                }
                FFileUtils.appendText(filepath, "<div class=\"dotted\">" + "\n<div class=\"exp\">\n" + FTimeUtils.dateToString(new Date()) + "\n</div><div>\n" + stackstr + "\n</div><div class=\"redcolor\">\n" + msg + "\n</div></div>", true);
            }
        });


    }


}
