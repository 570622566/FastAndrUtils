package com.hotapk.fastandrutils.statusbar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.hotapk.fastandrutils.R;

import cn.hotapk.fastandrutils.utils.FStatusBarUtils;

/**
 * @author laijian
 * @version 2017/11/7
 * @Copyright (C)下午5:10 , www.hotapk.cn
 * 伪装个状态栏宽高一样的view填充
 */
public class MarginTopBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_margin_top_bar);
        FStatusBarUtils.marginTopStatusBar(this, findViewById(R.id.title_name), ContextCompat.getColor(this, R.color.title));
    }
}
