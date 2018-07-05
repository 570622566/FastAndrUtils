package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.hotapk.fastandrutils.utils.FNetworkUtils;

/**
 * @author laijian
 * @version 2017/11/9
 * @Copyright (C)下午4:56 , www.hotapk.cn
 * 网络相关
 */
public class FNetworkActivity extends FBaseActivity {

    private TextView nettv;
    private TextView opentv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fnetwork);
        opentv = (TextView) findViewById(R.id.open_tv);
        nettv = (TextView) findViewById(R.id.net_tv);


        StringBuffer sb = new StringBuffer();
        sb.append("判断网络是否连接: " + FNetworkUtils.isConnected());
        sb.append("\n网络连接是否打开: " + FNetworkUtils.isNetworkAvailable());
        sb.append("\n判断当前网络是否为wifi: " + FNetworkUtils.isWifi());
        sb.append("\n判断当前网络是否为3g: " + FNetworkUtils.is3G());
        sb.append("\n判断当前网络是否为4g: " + FNetworkUtils.is4G());
        sb.append("\n获取当前ip地址: " + FNetworkUtils.getIPAddress());

        nettv.setText(sb);

        opentv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FNetworkUtils.openWirelessSettings();
            }
        });
    }
}
