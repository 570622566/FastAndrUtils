package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import cn.hotapk.fastandrutils.utils.FStatusBarUtils;

/**
 * @author laijian
 * @version 2017/11/3
 * @Copyright (C)上午11:10 , www.hotapk.cn
 */
public class FBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FStatusBarUtils.setSysStatusBar(this, ContextCompat.getColor(this, R.color.title));
    }
}
