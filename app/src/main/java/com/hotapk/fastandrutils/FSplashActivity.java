package com.hotapk.fastandrutils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import cn.hotapk.fastandrutils.utils.FAppUtils;

/**
 * @author laijian
 * @version 2017/11/2
 * @Copyright (C)下午3:24 , www.hotapk.cn
 */
public class FSplashActivity extends FBaseActivity {


    private TextView version_tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);
        version_tv = (TextView) findViewById(R.id.version_tv);
        version_tv.setText("V " + FAppUtils.getVerName());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(FSplashActivity.this, FMainActivity.class));
                        finish();
                    }
                });
            }
        }, 2000);
    }
}
