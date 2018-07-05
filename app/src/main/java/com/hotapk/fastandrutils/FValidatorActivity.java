package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.widget.TextView;

import cn.hotapk.fastandrutils.utils.FValidatorUtils;

/**
 * @author laijian
 * @version 2017/11/9
 * @Copyright (C)下午2:33 , www.hotapk.cn
 * 简单数据校验相关
 */
public class FValidatorActivity extends FBaseActivity {

    private TextView validator_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fvalidator);
        validator_tv = (TextView) findViewById(R.id.validator_tv);
        StringBuffer sb = new StringBuffer();
        sb.append("\n11121222222是否为手机号码：" + FValidatorUtils.isMobileExact("11121222222"));
        sb.append("\nassss@qq.comd是否为邮箱：" + FValidatorUtils.isEmail("assss@qq.comd"));
        sb.append("\nbaidu.dd是否为url：" + FValidatorUtils.isURL("baidu.dd"));
        validator_tv.setText(sb);
    }
}
