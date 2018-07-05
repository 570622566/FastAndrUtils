package com.hotapk.fastandrutils.bean;

/**
 * @author laijian
 * @version 2017/11/2
 * @Copyright (C)下午5:42 , www.hotapk.cn
 */
public class TitleInfor {
    private String titleName = "";
    private int type = 1;

    public TitleInfor() {
    }

    public TitleInfor(String titleName) {
        this.titleName = titleName;
    }

    public TitleInfor(String titleName, int type) {
        this.titleName = titleName;
        this.type = type;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "titleName==" + titleName;
    }

}
