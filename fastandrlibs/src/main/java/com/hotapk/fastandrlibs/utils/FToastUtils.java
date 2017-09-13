package com.hotapk.fastandrlibs.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hotapk.fastandrlibs.config.FToastConf;


/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)上午10:23 , www.hotapk.cn
 * Toast类
 */
public class FToastUtils {
    private static volatile FToastUtils toastUtils;
    private FToastConf conf;
    private Toast toast;
    private Context context;

    private float textSize = -1f;//字体大小
    private int textColorId = -1;//字体颜色id
    private int grivity = Gravity.BOTTOM;//显示位置
    private int xOffset = 0;//x偏移量
    private int yOffset = 60;//y偏移量
    private int imgResid = -1;//图片资源id
    private int duration = Toast.LENGTH_SHORT;//显示时间
    private int roundRadius = -1;//背景圆角
    private String bgcolor = null;//背景颜色
    private View rootView;
    private LinearLayout bglayout;
    private TextView mTvToast;
    private ImageView toastImg;

    private FToastUtils(Context context) {
        this.context = context;
        rootView = LayoutInflater.from(context.getApplicationContext()).inflate(FResourcesUtils.getLayoutResources(context.getApplicationContext(), "f_toast_layout"), null);//自定义样式，自定义布局文件
        bglayout = (LinearLayout) rootView.findViewById(
                FResourcesUtils.getIdResources(context.getApplicationContext(), "f_toast_bg"));
        mTvToast = (TextView) rootView.findViewById(
                FResourcesUtils.getIdResources(context.getApplicationContext(), "f_toast_tv"));
        toastImg = (ImageView) rootView.findViewById(
                FResourcesUtils.getIdResources(context.getApplicationContext(), "f_toast_iv"));
    }

    public static FToastUtils getInstant(Context context) {
        if (toastUtils == null) {
            synchronized (FToastUtils.class) {
                if (toastUtils == null) {
                    toastUtils = new FToastUtils(context);
                }
            }
        }
        return toastUtils;
    }

    public FToastUtils setConf(FToastConf conf) {
        this.conf = conf;
        textSize = conf.getTextSize();
        textColorId = conf.getTextColorId();
        grivity = conf.getGrivity();
        xOffset = conf.getxOffset();
        yOffset = conf.getyOffset();
        imgResid = conf.getImgResid();
        roundRadius = conf.getRoundRadius();
        bgcolor = conf.getBgcolor();
        return toastUtils;
    }

    public void showLongMsg(String msg) {
        duration = Toast.LENGTH_LONG;
        showMessage(msg);
    }

    public void showMessage(final String msg) {
        synchronized (FToastUtils.class) {
            cancelCurrentToast();
            if (conf != null) {
                toast = new Toast(context);
                mTvToast.setText(msg);
                setLayoutConf();
                toast.setView(rootView);//设置自定义的view
                toast.setGravity(grivity, xOffset, yOffset);
            } else {
                toast = Toast.makeText(context.getApplicationContext(), msg, duration);
            }
            toast.show();
            conf = null;
        }
    }

    private void setLayoutConf() {
        GradientDrawable gd = new GradientDrawable();// 创建drawable
        gd.setCornerRadius(roundRadius == -1 ? FScreenUtils.dip2px(context, 8) : FScreenUtils.dip2px(context, roundRadius));
        int fillColor = Color.parseColor(bgcolor == null ? "#66000000" : bgcolor);// 内部填充颜色
        gd.setColor(fillColor);//添加背景颜色
        bglayout.setBackground(gd);
        mTvToast.setTextSize(textSize != -1 ? textSize : 14);
        mTvToast.setTextColor(textColorId != -1 ? textColorId : Color.WHITE);
        if (imgResid != -1) {
            toastImg.setBackgroundResource(imgResid);
            toastImg.setVisibility(View.VISIBLE);
        } else {
            toastImg.setVisibility(View.GONE);
        }

    }

    public void cancelCurrentToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }


}
