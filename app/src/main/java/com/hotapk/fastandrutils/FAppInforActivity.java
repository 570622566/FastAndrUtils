package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.widget.TextView;

import cn.hotapk.fastandrutils.utils.FAppUtils;

/**
 * @author laijian
 * @version 2017/11/2
 * @Copyright (C)下午3:24 , www.hotapk.cn
 * 获取应用的相关信息
 */
public class FAppInforActivity extends FBaseActivity {
    private TextView appinfor_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_infor);
        appinfor_tv = (TextView) findViewById(R.id.appinfor_tv);
        StringBuffer sb = new StringBuffer();

        sb.append("\nApp包名：" + FAppUtils.getAppPackageName());
        sb.append("\nApp名称：" + FAppUtils.getAppName());
        sb.append("\nApp版本号：" + FAppUtils.getVerCode());
        sb.append("\nApp版本名称：" + FAppUtils.getVerName());
        sb.append("\nApp应用签名：" + FAppUtils.getSign(FAppUtils.getAppPackageName()));
        appinfor_tv.setText(sb);

    }
}
