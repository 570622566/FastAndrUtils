package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.widget.TextView;

import cn.hotapk.fastandrutils.utils.FDeviceUtils;

/**
 * @author laijian
 * @version 2017/11/3
 * @Copyright (C)下午4:23 , www.hotapk.cn
 * 设备信息
 */
public class FDeviceActivity extends FBaseActivity {

    private TextView convert_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdevice);
        convert_tv = (TextView) findViewById(R.id.convert_tv);
        StringBuffer sb = new StringBuffer();

        sb.append("\n判断是否为手机：" + FDeviceUtils.isPhone());
        sb.append("\n获取imei：" + FDeviceUtils.getIMEI());
        sb.append("\n获取imsi：" + FDeviceUtils.getIMSI());
        sb.append("\n获取移动终端类型：" + FDeviceUtils.getPhoneType());
        sb.append("\n判断sim卡是否准备好：" + FDeviceUtils.isSimCardReady());
        sb.append("\n网络运营商名称：" + FDeviceUtils.getNetworkOperatorName());
        sb.append("\n获取手机状态信息：\n" + FDeviceUtils.getPhoneStatus());
        convert_tv.setText(sb);
    }
}
