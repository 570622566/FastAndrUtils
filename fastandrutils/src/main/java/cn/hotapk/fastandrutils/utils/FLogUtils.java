package cn.hotapk.fastandrutils.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

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
    private static final String Tag = "www.hotapk.cn";
    private volatile static FLogUtils fLogUtils;
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
    private String TOP_BORDER = "═══════════════════════════════════════════════════════════════════════════════════════════════════════════";
    private String BOTTOM_BORDER = "═══════════════════════════════════════════════════════════════════════════════════════════════════════════";
    private String logDir = "";//设置文件存储目录
    private boolean debug = true;//是否打印log
    private boolean savesd = false;//是否存log到sd卡
    private boolean savecrash = true;//是否存crash信息到sd卡
    private int CHUNK_SIZE = 120; //设置字节数
    private long logSize = 1 * 1024 * 1024L;//设置log文件大小 k
    private ExecutorService execu = Executors.newFixedThreadPool(1);
    private FLogNetServer testHttpd;

    private FLogUtils() {
        Log.e(Tag, TOP_LINE);
        initLogFile();
    }

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

    private void initLogFile() {
        logDir = FFileUtils.getRootDir() + "/FastAndrUtils_log";
        FFileUtils.mkDir(logDir);
    }

    public void v(@NonNull Object objMsg) {
        v(Tag, objMsg);
    }

    public void d(@NonNull Object objMsg) {
        d(Tag, objMsg);
    }

    public void i(@NonNull Object objMsg) {
        i(Tag, objMsg);
    }

    public void w(@NonNull Object objMsg) {
        w(Tag, objMsg);
    }

    public void e(@NonNull Object objMsg) {
        e(Tag, objMsg);
    }

    public void v(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.v(tag, msgFormat(stackstr, objMsg));
        }
        if (savesd) {
            saveToSd(stackstr, objMsg);
        }
    }

    public void d(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.d(tag, msgFormat(stackstr, objMsg));
        }
        if (savesd) {
            saveToSd(stackstr, objMsg);
        }
    }

    public void i(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.i(tag, msgFormat(stackstr, objMsg));
        }
        if (savesd) {
            saveToSd(stackstr, objMsg);
        }
    }

    public void w(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.w(tag, msgFormat(stackstr, objMsg));
        }
        if (savesd) {
            saveToSd(stackstr, objMsg);
        }
    }


    public void e(String tag, @NonNull Object objMsg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.e(tag, msgFormat(stackstr, objMsg));
        }
        if (savesd) {
            saveToSd(stackstr, objMsg);
        }
    }

    /**
     * app异常时使用
     *
     * @param msg
     */
    public void setCrash(String msg) {
        String stackstr = targetStackTraceMSg();
        if (debug) {
            Log.e("www.hotapk.cn", msgFormat(stackstr, msg));
        }
        if (savecrash) {
            saveToSd(stackstr, msg);
        }
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


    public boolean isDebug() {
        return debug;
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
     * 是否保存crash信息
     *
     * @param savecrash
     * @return
     */
    public FLogUtils saveCrash(boolean savecrash) {
        this.savecrash = savecrash;
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


    /**
     * 启动log的WebServer服务
     *
     * @param port 端口号
     */
    public void startLogServer(int port) {
        if (testHttpd == null) {
            synchronized (FLogUtils.class) {
                if (testHttpd == null) {
                    testHttpd = new FLogNetServer(port);
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

    private String msgFormat(String stackstr, Object objMsg) {
        String msg = "";
        if (objMsg instanceof String) {
            msg = objMsg.toString();
        } else {
            msg = new Gson().toJson(objMsg);
            msg = FConvertUtils.jsonFormatter(msg);
        }
        byte[] bytes = new byte[0];
        try {
            bytes = msg.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int length = bytes.length;
        String newMsg = TOP_BORDER + "\n" + FDateUtils.dateToString(new Date()) + "\n" + stackstr;
        if (length > CHUNK_SIZE) {
            int i = 0;
            while (i < length) {
                int count = Math.min(length - i, CHUNK_SIZE);
                String tempStr = new String(bytes, i, count);
                newMsg += "\n" + tempStr;
                i += CHUNK_SIZE;
            }
        } else {
            newMsg += "\n" + msg;
        }
        newMsg += "\n" + BOTTOM_BORDER;
        return newMsg;

    }

    private void saveToSd(final String stackstr, final Object objMsg) {
        execu.submit(new Runnable() {
            @Override
            public void run() {
                String data = FDateUtils.dateToString(new Date(), "yyyy-MM-dd");
                File[] files = FFileUtils.orderByDate(new File(logDir), true);
                List<File> filels = FFileUtils.filter(files, data);
                String filepath;
                if (filels.size() > 0) {
                    Long length = FFileUtils.getLength(filels.get(0));
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
                String msg = "";
                if (objMsg instanceof String) {
                    msg = objMsg.toString();
                } else {
                    msg = FConvertUtils.jsonFormatter(new Gson().toJson(objMsg));
                }
                FFileUtils.appendText(filepath, "<div class=\"dotted\">" + "\n<div class=\"exp\">\n" + FDateUtils.dateToString(new Date()) + "\n</div><div>\n" + stackstr + "\n</div><div class=\"redcolor\">\n" + msg.replaceAll("\n", "<br />") + "\n</div></div>", true);
            }
        });


    }


}
