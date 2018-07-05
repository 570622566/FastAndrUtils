package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.hotapk.fastandrutils.utils.FClickProxy;
import cn.hotapk.fastandrutils.utils.FToastUtils;

/**
 * @author laijian
 * @version 2017/11/2
 * @Copyright (C)下午3:24 , www.hotapk.cn
 * 获取应用的相关信息
 */
public class FCheckDoubleClickActivity extends FBaseActivity {


    Button checkdc_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_double_click);
        checkdc_bt = (Button) findViewById(R.id.checkdc_bt);
        checkdc_bt.setOnClickListener(new FClickProxy(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }, new FClickProxy.IAgain() {
            @Override
            public void onAgain() {
                FToastUtils.init().show("请在1秒钟后再点击");
            }
        }, 1000
        ));

    }
}
