package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.view.View;

import cn.hotapk.fastandrutils.utils.FLogUtils;


/**
 * @author laijian
 * @Copyright (C)2018-06-19 00:46:18 , www.hotapk.cn
 */

public class LinearLayoutActivity extends FBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        findViewById(R.id.lin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FLogUtils.getInstance().e("ddddeee33");
            }
        });
    }

}
