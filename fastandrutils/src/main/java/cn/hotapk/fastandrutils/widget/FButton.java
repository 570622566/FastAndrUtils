package cn.hotapk.fastandrutils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import cn.hotapk.fastandrutils.R;

/**
 * @author laijian
 * @version 2018/6/19
 * @Copyright (C)上午12:40 , www.hotapk.cn
 * @since
 */
public class FButton extends Button {
    private Context context;
    private int bgColor;
    private int radius;
    private int stroke;
    private int strokeColor;
    private int btselbgColor;
    private float btPressAlpha;
    private GradientDrawable btDrawable;

    public FButton(Context context) {
        this(context, null);
    }

    public FButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FButton, 0, 0);
        init(context, typedArray);
        typedArray.recycle();
    }

    private void init(Context context, TypedArray typedArray) {
        this.context = context;
        bgColor = typedArray.getColor(R.styleable.FButton_btBgColor, ContextCompat.getColor(context, R.color.white_color));
        radius = (int) typedArray.getDimension(R.styleable.FButton_btRadius, 8f);
        stroke = (int) typedArray.getDimension(R.styleable.FButton_btStroke, 0f);
        strokeColor = typedArray.getColor(R.styleable.FButton_btStrokeColor, ContextCompat.getColor(context, R.color.tran_color));
        btselbgColor = typedArray.getColor(R.styleable.FButton_btselbgColor, ContextCompat.getColor(context, R.color.tran_color));
        btPressAlpha = typedArray.getFloat(R.styleable.FButton_btPressAlpha, 1f);
        setBg();
    }

    private void setBg() {
        btDrawable = new GradientDrawable();// 创建drawable
        btDrawable.setCornerRadius(radius);
        btDrawable.setStroke(stroke, strokeColor);
        btDrawable.setColor(bgColor);//添加背景颜色
        setBackground(btDrawable);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (btselbgColor != ContextCompat.getColor(context, R.color.tran_color)) {
                    btDrawable.setColor(btselbgColor);
                }
                setAlpha(btPressAlpha);
                break;
            case MotionEvent.ACTION_UP:
                btDrawable.setAlpha(255);
                setAlpha(1f);
                btDrawable.setColor(bgColor);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    public void setBgColor(@ColorRes int bgColor) {
        this.bgColor = bgColor;
        btDrawable.setColor(ContextCompat.getColor(context, bgColor));
    }

    public void setRadius(int radius) {
        this.radius = radius;
        btDrawable.setCornerRadius(radius);
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
        btDrawable.setStroke(stroke, strokeColor);

    }

    public void setStrokeColor(@ColorRes int strokeColor) {
        this.strokeColor = strokeColor;
        btDrawable.setStroke(stroke, ContextCompat.getColor(context, strokeColor));

    }

    public void setBtselbgColor(@ColorRes int btselbgColor) {
        this.btselbgColor = ContextCompat.getColor(context, btselbgColor);
    }

    public void setBtPressAlpha(@IntRange(from = 0, to = 255) int btPressAlpha) {
        this.btPressAlpha = btPressAlpha;
    }
}
