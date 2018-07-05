package com.hotapk.fastandrutils.statusbar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.hotapk.fastandrutils.R;

import cn.hotapk.fastandrutils.utils.FStatusBarUtils;

/**
 * @author laijian
 * @version 2017/11/7
 * @Copyright (C)下午5:20 , www.hotapk.cn
 * 兼容drawerLayout的状态栏
 */
public class DrawerBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_bar);
        FStatusBarUtils.setStatusBarView4Draw(this, ContextCompat.getColor(this, R.color.title));
    }
}
