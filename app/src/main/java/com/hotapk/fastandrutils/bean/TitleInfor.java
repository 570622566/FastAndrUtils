package com.hotapk.fastandrutils.bean;

/**
 * @author laijian
 * @version 2017/11/2
 * @Copyright (C)下午5:42 , www.hotapk.cn
 */
public class TitleInfor {
    private String titleName="";

    public TitleInfor() {
    }

    public TitleInfor(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    @Override
    public String toString() {
        return "titleName=="+titleName;
    }
}
