package cn.hotapk.fastandrutils.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)上午10:23 , www.hotapk.cn
 * Toast类
 */
public class FToastUtils {
    private volatile static FToastUtils toastUtils;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private Toast toast;
    private Context context;
    private View rootView;
    private LinearLayout bglayout;
    private TextView mTvToast;
    private ImageView toastImg;
    private float textSize = -1f;//字体大小
    private int textColorId = -1;//字体颜色id
    private int grivity = Gravity.BOTTOM;//显示位置
    private int xOffset = 0;//x偏移量
    private int yOffset = 80;//y偏移量
    private int imgResid = -1;//图片资源id
    private int duration = Toast.LENGTH_SHORT;//显示时间
    private int roundRadius = -1;//背景圆角
    private int bgcolor = 0;//背景颜色
    private boolean customView = false;//是否是自定义view
    private int viewDirection = LinearLayout.HORIZONTAL;//图标文字显示方式
    private long cancelTime = -1;

    private FToastUtils() {
        this.context = FUtils.getAppContext();

    }

    public static FToastUtils init() {
        if (toastUtils == null) {
            synchronized (FToastUtils.class) {
                if (toastUtils == null) {
                    toastUtils = new FToastUtils();
                }
            }
        }
        return toastUtils;
    }

    /**
     * 长时间显示Toast
     *
     * @param objMsg
     */
    public FToastUtils showLong(Object objMsg) {
        duration = Toast.LENGTH_LONG;
        show(objMsg);
        return this;
    }

    /**
     * 短时间显示Toast
     *
     * @param objMsg
     */
    public FToastUtils show(final Object objMsg) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (FToastUtils.class) {
                    cancelCurrentToast();
                    String msg = "";
                    if (objMsg instanceof String) {
                        msg = objMsg.toString();
                    } else {
                        msg = new Gson().toJson(objMsg);
                    }
                    if (customView) {
                        toast = new Toast(context);
                        setLayoutConf(msg);
                        toast.setView(rootView);//设置自定义的view
                        toast.setGravity(grivity, xOffset, yOffset);
                    } else {
                        toast = Toast.makeText(context.getApplicationContext(), msg, duration);
                    }
                    toast.show();
                    if (cancelTime != -1) {
                        cancelCurrentToast(cancelTime);
                    }
                    customView = false;
                    reset();

                }
            }
        });
//            }
//        }).start();
        return this;
    }

    /**
     * 配置view样式
     */
    private void setLayoutConf(String msg) {
        rootView = LayoutInflater.from(context.getApplicationContext()).inflate(FResourcesUtils.getLayoutResources("f_toast_layout"), null);//自定义样式，自定义布局文件
        bglayout = (LinearLayout) rootView.findViewById(
                FResourcesUtils.getIdResources("f_toast_bg"));
        mTvToast = (TextView) rootView.findViewById(
                FResourcesUtils.getIdResources("f_toast_tv"));
        toastImg = (ImageView) rootView.findViewById(
                FResourcesUtils.getIdResources("f_toast_iv"));

        LinearLayout.LayoutParams linearParams = null;
        LinearLayout.LayoutParams imgParams = null;
        LinearLayout.LayoutParams tvParams = null;
        GradientDrawable gd = new GradientDrawable();// 创建drawable
        gd.setCornerRadius(roundRadius == -1 ? FConvertUtils.dip2px(8) : FConvertUtils.dip2px(roundRadius));
        int fillColor = bgcolor == 0 ? FResourcesUtils.getColorResources("toastbg") : bgcolor;// 内部填充颜色
        gd.setColor(ContextCompat.getColor(context, fillColor));//添加背景颜色
        bglayout.setBackground(gd);
        bglayout.setOrientation(viewDirection);
        if (viewDirection == LinearLayout.HORIZONTAL) {
            linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            bglayout.setGravity(Gravity.CENTER_HORIZONTAL);
        } else {

            linearParams = new LinearLayout.LayoutParams(FConvertUtils.dip2px(162), FConvertUtils.dip2px(110));
            bglayout.setGravity(Gravity.CENTER);

        }
        bglayout.setLayoutParams(linearParams);
        if (imgResid != -1) {
            if (viewDirection == LinearLayout.HORIZONTAL) {
                imgParams = new LinearLayout.LayoutParams(FConvertUtils.dip2px(32), FConvertUtils.dip2px(32));
                imgParams.setMargins(0, 0, FConvertUtils.dip2px(10), 0);
            } else {
                imgParams = new LinearLayout.LayoutParams(FConvertUtils.dip2px(48), FConvertUtils.dip2px(48));
                imgParams.setMargins(0, 0, 0, FConvertUtils.dip2px(6));
            }
            toastImg.setLayoutParams(imgParams);
            toastImg.setBackgroundResource(imgResid);
            toastImg.setVisibility(View.VISIBLE);
        } else {
            toastImg.setVisibility(View.GONE);
        }

        tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (viewDirection == LinearLayout.HORIZONTAL) {
            tvParams.gravity = Gravity.CENTER_VERTICAL;
        } else {
            tvParams.gravity = Gravity.CENTER_HORIZONTAL;
        }
        mTvToast.setTextSize(textSize != -1 ? textSize : 14);
        mTvToast.setTextColor(textColorId != -1 ? textColorId : Color.WHITE);
        mTvToast.setLayoutParams(tvParams);
        if (grivity == Gravity.CENTER) {
            if (yOffset == 80) {
                yOffset = 0;
            }
        }
        mTvToast.setText(msg);
    }

    /**
     * 关闭toast
     */
    public void cancelCurrentToast() {
        if (toast != null) {
            toast.cancel();
            rootView = null;
            toast = null;
        }
    }

    /**
     * 多少秒关闭toast
     */
    public void cancelCurrentToast(long time) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    FLogUtils.getInstance().e("fcdddddd");
                    toast.cancel();
                    rootView = null;
                    toast = null;
                }
            }
        }, time);


    }


    /**
     * 设置字体大小
     *
     * @param textSize
     * @return
     */
    public FToastUtils setTextSize(float textSize) {
        this.customView = true;
        this.textSize = textSize;
        return this;

    }

    /**
     * 设置字体颜色
     *
     * @param textColorId
     * @return
     */
    public FToastUtils setTextColor(@ColorRes int textColorId) {
        this.customView = true;
        this.textColorId = textColorId;
        return this;

    }

    /**
     * 显示位置
     *
     * @param grivity
     * @return
     */
    public FToastUtils setGrivity(int grivity) {
        this.customView = true;
        this.grivity = grivity;
        return this;
    }

    /**
     * x偏移量
     *
     * @param xOffset
     * @return
     */
    public FToastUtils setXOffset(int xOffset) {
        this.customView = true;
        this.xOffset = xOffset;
        return this;
    }

    /**
     * y偏移量
     *
     * @param yOffset
     * @return
     */
    public FToastUtils setYOffset(int yOffset) {
        this.customView = true;
        this.yOffset = yOffset;
        return this;
    }

    /**
     * 设置图片资源id
     *
     * @param imgResid
     * @return
     */
    public FToastUtils setImgResid(@DrawableRes int imgResid) {
        this.customView = true;
        this.imgResid = imgResid;
        return this;
    }

    /**
     * 自定义关闭时间
     *
     * @param cancelTime
     * @return
     */
    public FToastUtils setDuration(long cancelTime) {
        this.cancelTime = cancelTime;
        return this;
    }


    /**
     * 设置圆角大小
     *
     * @param roundRadius
     * @return
     */
    public FToastUtils setRoundRadius(int roundRadius) {
        this.customView = true;
        this.roundRadius = roundRadius;
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param bgcolor
     * @return
     */
    public FToastUtils setBgcolor(@ColorRes int bgcolor) {
        this.customView = true;
        this.bgcolor = bgcolor;
        return this;
    }

    /**
     * 设置图片文字显示方向 横向，纵向
     *
     * @param viewDirection
     * @return
     */
    public FToastUtils setDirection(@Direction int viewDirection) {
        this.customView = true;
        this.viewDirection = viewDirection;
        return this;
    }

    private void reset() {
        textSize = -1f;//字体大小
        textColorId = -1;//字体颜色id
        grivity = Gravity.BOTTOM;//显示位置
        xOffset = 0;//x偏移量
        yOffset = 80;//y偏移量
        imgResid = -1;//图片资源id
        duration = Toast.LENGTH_SHORT;//显示时间
        roundRadius = -1;//背景圆角
        bgcolor = 0;//背景颜色
        viewDirection = LinearLayout.HORIZONTAL;
        cancelTime = -1;
    }

    @IntDef({LinearLayout.HORIZONTAL, LinearLayout.VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
    }

}
