package cn.hotapk.fastandrutils.config;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IntDef;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cn.hotapk.fastandrutils.R;
import cn.hotapk.fastandrutils.fragment.FAlertDialog;

/**
 * @author laijian
 * @version 2018/7/2
 * @Copyright (C)下午5:05 , www.hotapk.cn
 * @since
 */
public class FAlertDialogBuilder extends FBaseDialogBuilder<FAlertDialogBuilder, FAlertDialog> {

    //字体大小
    private float titleTextSize = 16;
    private float contentTextSize = 14;
    private float leftTextSize = 14;
    private float rightTextSize = 14;
    //内容
    private String titleText = "提示";
    private String contentText = "";
    private String leftText = "取消";
    private String rightText = "确定";
    //字体颜色
    private int titleTextColor = R.color.title_color;
    private int contentTextColor = R.color.content_color;
    private int leftTextColor = R.color.dialogbt_color;
    private int rightTextColor = R.color.dialogbt_color;
    private int visibilityBt = View.VISIBLE;
    private int visibilityTitle = View.VISIBLE;
    private FAlertDialog.OnLeftClickListener onLeftClickListener;
    private FAlertDialog.OnRightClickListener onRightClickListener;

    public FAlertDialogBuilder(FragmentActivity appCompatActivity) {
        super(appCompatActivity);
    }

    public FAlertDialogBuilder setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
        return this;
    }

    public FAlertDialogBuilder setContentTextSize(float contentTextSize) {
        this.contentTextSize = contentTextSize;
        return this;
    }

    public FAlertDialogBuilder setLeftTextSize(float leftTextSize) {
        this.leftTextSize = leftTextSize;
        return this;
    }

    public FAlertDialogBuilder setRightTextSize(float rightTextSize) {
        this.rightTextSize = rightTextSize;
        return this;
    }

    public FAlertDialogBuilder setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }

    public FAlertDialogBuilder setContentText(String contentText) {
        this.contentText = contentText;
        return this;
    }

    public FAlertDialogBuilder setLeftText(String leftText) {
        this.leftText = leftText;
        return this;
    }

    public FAlertDialogBuilder setRightText(String rightText) {
        this.rightText = rightText;
        return this;
    }

    public FAlertDialogBuilder setTitleTextColor(@ColorRes int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    public FAlertDialogBuilder setContentTextColor(@ColorRes int contentTextColor) {
        this.contentTextColor = contentTextColor;
        return this;
    }

    public FAlertDialogBuilder setLeftTextColor(@ColorRes int leftTextColor) {
        this.leftTextColor = leftTextColor;
        return this;
    }

    public FAlertDialogBuilder setRightTextColor(@ColorRes int rightTextColor) {
        this.rightTextColor = rightTextColor;
        return this;
    }

    public FAlertDialogBuilder setVisibilityBt(@Visibility int visibilityBt) {
        this.visibilityBt = visibilityBt;
        return this;
    }

    public FAlertDialogBuilder setVisibilityTitle(@Visibility int visibilityTitle) {
        this.visibilityTitle = visibilityTitle;
        return this;
    }

    public FAlertDialogBuilder setOnLeftClickListener(FAlertDialog.OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
        return this;
    }

    public FAlertDialogBuilder setOnRightClickListener(FAlertDialog.OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
        return this;
    }

    @Override
    protected Class<?> dialogClass() {
        return FAlertDialog.class;
    }

    @Override
    protected void setBundle(Bundle bundle) {
        bundle.putFloat(FAlertDialog.FDIALOG_TITLETEXTSIZE, titleTextSize);
        bundle.putFloat(FAlertDialog.FDIALOG_CONTENTTEXTSIZE, contentTextSize);
        bundle.putFloat(FAlertDialog.FDIALOG_LEFTTEXTSIZE, leftTextSize);
        bundle.putFloat(FAlertDialog.FDIALOG_RIGHTTEXTSIZE, rightTextSize);

        bundle.putString(FAlertDialog.FDIALOG_TITLETEXT, titleText);
        bundle.putString(FAlertDialog.FDIALOG_CONTENTTEXT, contentText);
        bundle.putString(FAlertDialog.FDIALOG_LEFTTEXT, leftText);
        bundle.putString(FAlertDialog.FDIALOG_RIGHTTEXT, rightText);

        bundle.putInt(FAlertDialog.FDIALOG_TITLETEXTCOLOR, titleTextColor);
        bundle.putInt(FAlertDialog.FDIALOG_CONTENTTEXTCOLOR, contentTextColor);
        bundle.putInt(FAlertDialog.FDIALOG_LEFTTEXTCOLOR, leftTextColor);
        bundle.putInt(FAlertDialog.FDIALOG_RIGHTTEXTCOLOR, rightTextColor);

        bundle.putInt(FAlertDialog.FDIALOG_HIDELEFTBT, visibilityBt);
        bundle.putInt(FAlertDialog.FDIALOG_VISIBILITY, visibilityTitle);


    }

    @Override
    protected void setListener(FAlertDialog dialog) {
        dialog.setOnLeftClickListener(onLeftClickListener);
        dialog.setOnRightClickListener(onRightClickListener);
    }

    @IntDef({View.VISIBLE, View.INVISIBLE, View.GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }
}
