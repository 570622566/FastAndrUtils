package com.hotapk.fastandrutils.statusbar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.hotapk.fastandrutils.R;

import cn.hotapk.fastandrutils.utils.FStatusBarUtils;

/**
 * @author laijian
 * @version 2017/11/7
 * @Copyright (C)下午5:01 , www.hotapk.cn
 * 系统状态栏
 */
public class SysBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_bar);
        FStatusBarUtils.setSysStatusBar(this, ContextCompat.getColor(this, R.color.title));

    }
}
