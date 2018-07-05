package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.view.View;

import cn.hotapk.fastandrutils.utils.FToastUtils;
import cn.hotapk.fastandrutils.widget.FButton;


/**
 * @author laijian
 * @Copyright (C)2018-06-27 14:43:57 , www.hotapk.cn
 */

public class FButtonActivity extends FBaseActivity {
    FButton fButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbutton);
        fButton = findViewById(R.id.demo_bt);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FToastUtils.init().show("点击");
            }
        });
    }
}
