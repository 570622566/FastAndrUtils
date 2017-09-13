package com.hotapk.fastandrlibs.utils;

import android.util.Log;

import com.hotapk.fastandrlibs.config.FLogConf;

import java.io.File;
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
    private long logSize = 2 * 1024 * 1024L;//设置log文件大小 k
    private ExecutorService execu = Executors.newFixedThreadPool(1);

    private volatile static FLogUtils fLogUtils;

    private FLogUtils() {
        Log.e("www.hotapk.cn_log", TOP_LINE);
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

    public FLogUtils setConf(FLogConf fLogConf) {
        debug = fLogConf.isDebug();
        savesd = fLogConf.isSavesd();
        logSize = fLogConf.getLogSize();
        logDir = fLogConf.getLogDir();
        return this;
    }

    private void initLogFile() {
        logDir = FFileUtils.getRootDir() + "/hotapk.cn";
        FFileUtils.mkDir(logDir);
    }


    private void saveToSd(final String tag, final String msg) {
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
                        int index = Integer.parseInt(filels.get(0).getName().replace(data + "_", "").replace(".log", ""));
                        int id = index + 1;
                        filepath = logDir + data + "_" + id + ".log";
                        FFileUtils.creatFile(filepath);
                    } else {
                        filepath = filels.get(0).getAbsolutePath();
                    }

                } else {
                    filepath = logDir + data + "_1.log";
                    FFileUtils.creatFile(filepath);
                }
                FFileUtils.appendText(new File(filepath), "\r\n" + tag + "\n" + msg);

            }
        });


    }

    private String msgFormat(String msg) {
        byte[] bytes = new byte[0];
        try {
            bytes = msg.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int length = bytes.length;
        String newMsg = TOP_BORDER + "\n" + LEFT_BORDER + "\t" + FTimeUtils.dateToString(new Date()) + "\n" + LEFT_BORDER + "\t" + targetStackTraceMSg();
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


    private String targetStackTraceMSg() {
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        if (targetStackTraceElement != null) {
            return "at " + targetStackTraceElement.getClassName() +"."+ targetStackTraceElement.getMethodName()+
                   "(" + targetStackTraceElement.getFileName() + ":" + targetStackTraceElement.getLineNumber()+")";

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


    public void v(String msg) {
        v("www.hotapk.cn_log", msg);
    }

    public void d(String msg) {
        d("www.hotapk.cn_log", msg);
    }

    public void i(String msg) {
        i("www.hotapk.cn_log", msg);
    }

    public void w(String msg) {
        w("www.hotapk.cn_log", msg);
    }

    public void e(String msg) {
        e("www.hotapk.cn_log", msg);
    }

    public void v(String tag, String msg) {
        if (debug) {
            Log.v(tag, msgFormat(msg));
        }
        saveToSd(tag, msg);
    }

    public void d(String tag, String msg) {
        if (debug) {
            Log.d(tag, msgFormat(msg));
        }
        saveToSd(tag, msg);
    }

    public void i(String tag, String msg) {
        if (debug) {
            Log.i(tag, msgFormat(msg));
        }
        saveToSd(tag, msg);
    }

    public void w(String tag, String msg) {
        if (debug) {
            Log.w(tag, msgFormat(msg));
        }
        saveToSd(tag, msg);
    }


    public void e(String tag, String msg) {
        if (debug) {
            Log.e(tag, msgFormat(msg));
        }
        saveToSd(tag, msg);
    }

}
