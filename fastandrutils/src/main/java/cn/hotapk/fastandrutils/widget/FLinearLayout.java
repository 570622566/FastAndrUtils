package cn.hotapk.fastandrutils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import cn.hotapk.fastandrutils.R;

/**
 * @author laijian
 * @version 2018/6/19
 * @Copyright (C)上午12:40 , www.hotapk.cn
 * @since
 */
public class FLinearLayout extends LinearLayout {
    private Context context;
    private int bgColor;
    private int radius;
    private int stroke;
    private int strokeColor;
    private GradientDrawable gradientDrawable;

    public FLinearLayout(Context context) {
        this(context, null);
    }

    public FLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FLinearLayout, 0, 0);
        init(context, typedArray);
        typedArray.recycle();
    }

    private void init(Context context, TypedArray typedArray) {
        this.context = context;
        bgColor = typedArray.getColor(R.styleable.FLinearLayout_bgColor, ContextCompat.getColor(context, R.color.white_color));
        radius = (int) typedArray.getDimension(R.styleable.FLinearLayout_radius, 8f);
        stroke = (int) typedArray.getDimension(R.styleable.FLinearLayout_stroke, 0f);
        strokeColor = typedArray.getColor(R.styleable.FLinearLayout_strokeColor, ContextCompat.getColor(context, R.color.tran_color));
        setBg();
    }

    private void setBg() {
        gradientDrawable = new GradientDrawable();// 创建drawable
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setStroke(stroke, strokeColor);
        gradientDrawable.setColor(bgColor);//添加背景颜色
        setBackground(gradientDrawable);
    }

    public void setBgColor(@ColorRes int bgColor) {
        this.bgColor = bgColor;
        gradientDrawable.setColor(ContextCompat.getColor(context, bgColor));//添加背景颜色

    }

    public void setRadius(int radius) {
        this.radius = radius;
        gradientDrawable.setCornerRadius(radius);
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
        gradientDrawable.setStroke(stroke, strokeColor);

    }

    public void setStrokeColor(@ColorRes int strokeColor) {
        this.strokeColor = strokeColor;
        gradientDrawable.setStroke(stroke, ContextCompat.getColor(context, strokeColor));

    }
}
