package com.hotapk.fastandrlibs.config;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)下午4:52 , www.hotapk.cn
 */
public class FLogConf {
    private boolean debug = true;//是否打印log
    private boolean savesd = false;//是否存log到sd卡
    private String logDir = "";//设置文件存储目录
    private long logSize = 2 * 1024 * 1024L;//设置log文件大小 k

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isSavesd() {
        return savesd;
    }

    public void setSavesd(boolean savesd) {
        this.savesd = savesd;
    }

    public String getLogDir() {
        return logDir;
    }

    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }

    public long getLogSize() {
        return logSize;
    }

    public void setLogSize(long logSize) {
        this.logSize = logSize;
    }
}
