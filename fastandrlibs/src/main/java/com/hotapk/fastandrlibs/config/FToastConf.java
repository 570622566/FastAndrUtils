package com.hotapk.fastandrlibs.config;

import android.view.Gravity;

/**
 * @author laijian
 * @version 2017/9/8
 * @Copyright (C)下午4:29 , www.hotapk.cn
 */
public class FToastConf {

    private float textSize = -1f;//字体大小
    private int textColorId = -1;//字体颜色id
    private int grivity = Gravity.BOTTOM;//显示位置
    private int xOffset = 0;//显示位置的x偏移量
    private int yOffset = 60;//显示位置的y偏移量
    private int imgResid = -1;//显示icon的id
    private int roundRadius = -1;//背景圆角
    private String bgcolor = null;//背景颜色

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getTextColorId() {
        return textColorId;
    }

    public void setTextColorId(int textColorId) {
        this.textColorId = textColorId;
    }

    public int getGrivity() {
        return grivity;
    }

    public void setGrivity(int grivity) {
        this.grivity = grivity;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getImgResid() {
        return imgResid;
    }

    public void setImgResid(int imgResid) {
        this.imgResid = imgResid;
    }

    public int getRoundRadius() {
        return roundRadius;
    }

    public void setRoundRadius(int roundRadius) {
        this.roundRadius = roundRadius;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }
}
