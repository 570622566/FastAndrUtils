package com.hotapk.fastandrutils.statusbar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.hotapk.fastandrutils.R;

import cn.hotapk.fastandrutils.utils.FStatusBarUtils;

/**
 * @author laijian
 * @version 2017/11/7
 * @Copyright (C)下午5:42 , www.hotapk.cn
 * 状态栏的透明度随着标题栏的高度变化
 */
public class ChangeBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bar);
        FStatusBarUtils.paddingTopStatusBar(this, findViewById(R.id.title_name));
        FStatusBarUtils.setAlphaStatusBar(this, findViewById(R.id.title_name), ContextCompat.getColor(this, R.color.colorAccent));
    }
}
