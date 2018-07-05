package com.hotapk.fastandrutils.statusbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hotapk.fastandrutils.R;

import cn.hotapk.fastandrutils.utils.FStatusBarUtils;

/**
 * @author laijian
 * @version 2017/11/7
 * @Copyright (C)下午4:15 , www.hotapk.cn
 * 透明状态栏
 */

public class TransBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_bar);
        FStatusBarUtils.translucent(this);

    }
}
