package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.widget.TextView;

import com.hotapk.fastandrutils.bean.TitleInfor;

import java.util.HashMap;
import java.util.Map;

import cn.hotapk.fastandrutils.utils.FConvertUtils;

/**
 * @author laijian
 * @version 2017/11/3
 * @Copyright (C)下午2:44 , www.hotapk.cn
 * 数据转换工具类
 */
public class FConvertActivity extends FBaseActivity {


    private TextView convert_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fconvert);
        convert_tv = (TextView) findViewById(R.id.convert_tv);
        StringBuffer sb = new StringBuffer();
        sb.append("\n10 px值转换为dp值：" + FConvertUtils.px2dip(10));
        sb.append("\n10 px值转换为sp值：" + FConvertUtils.px2sp(10));
        sb.append("\n102345 转换为网速格式：" + FConvertUtils.binaryFormatSize(102345));
        TitleInfor titleInfor = new TitleInfor();
        titleInfor.setTitleName("对象转map");

        try {
            sb.append("\n对象转啊map：" + FConvertUtils.obj2Map(titleInfor));
            Map<String, String> map = new HashMap<>();
            map.put("titleName", "map转对象");
            TitleInfor titleInfor2 = (TitleInfor) FConvertUtils.map2Obj(map, TitleInfor.class);
            sb.append("\nmap转对象：" + titleInfor2.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        convert_tv.setText(sb.toString());

    }
}
