package cn.hotapk.fastandrutils.config;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import cn.hotapk.fastandrutils.fragment.FLoaddingDialog;

/**
 * @author laijian
 * @version 2018/7/2
 * @Copyright (C)上午9:49 , www.hotapk.cn
 * @since
 */
public class FLoaddingBuilder extends FBaseDialogBuilder<FLoaddingBuilder, FLoaddingDialog> {

    private String title = "加载中。。。";
    private int orientationMode = 1;

    public FLoaddingBuilder(FragmentActivity appCompatActivity) {
        super(appCompatActivity);
    }


    public FLoaddingBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public FLoaddingBuilder setOrientationMode(@FLoaddingDialog.OrientationMode int orientationMode) {
        this.orientationMode = orientationMode;
        return this;
    }

    @Override
    protected Class<FLoaddingDialog> dialogClass() {
        return FLoaddingDialog.class;
    }

    @Override
    protected void setBundle(Bundle bundle) {
        bundle.putString(FLoaddingDialog.LOADDINGTITLE, title);
        bundle.putInt(FLoaddingDialog.LOADDINGORIENTATION, orientationMode);
    }

    @Override
    protected void setListener(FLoaddingDialog dialog) {

    }
}
