package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

import cn.hotapk.fastandrutils.utils.FDateUtils;

/**
 * @author laijian
 * @version 2017/11/9
 * @Copyright (C)下午2:20 , www.hotapk.cn
 * 时间类
 */
public class FDateActivity extends FBaseActivity {
    private TextView date_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdate);
        date_tv = (TextView) findViewById(R.id.date_tv);
        StringBuffer stringBuffer = new StringBuffer();

//        默认时间类型 yyyy-MM-dd HH:mm:ss
        stringBuffer.append("Date转字符串：" + FDateUtils.dateToString(new Date()));
        // 格式：年－月－日
        stringBuffer.append("\nDate转字符串：" + FDateUtils.dateToString(new Date(), FDateUtils.FORMAT_YMD));
//        默认时间类型 yyyy-MM-dd HH:mm:ss
        stringBuffer.append("\n字符串转Date：" + FDateUtils.stringToDate(FDateUtils.dateToString(new Date())));
        // 格式：年－月－日
        stringBuffer.append("\n字符串转Date：" + FDateUtils.stringToDate(FDateUtils.dateToString(new Date()), FDateUtils.FORMAT_YMD));
        stringBuffer.append("\n获取本月第一天：" + FDateUtils.getFirstDayOfMonth(FDateUtils.FORMAT_YMDHMS));
        stringBuffer.append("\n获取本月最后天：" + FDateUtils.getLastDayOfMonth(FDateUtils.FORMAT_YMDHMS));
        // 星期天为第一天
        stringBuffer.append("\n获取每月的第一周天数：" + FDateUtils.getFirstWeekdayOfMonth(2017, 11));
        // 本周一0点时间
        stringBuffer.append("\n获取本周一0点时间：" + FDateUtils.dateToString(FDateUtils.getTimesWeekmorning()));
        //本周日24点时间
        stringBuffer.append("\n获取本周日24点时间：" + FDateUtils.dateToString(FDateUtils.getTimesWeeknight()));
        //获取前7天
        stringBuffer.append("\n获取前7天时间：" + FDateUtils.dateToString(FDateUtils.getBefore(new Date())));
        //获取后7天
        stringBuffer.append("\n获取后7天时间：" + FDateUtils.dateToString(FDateUtils.getAfterDate(new Date())));

        date_tv.setText(stringBuffer);
    }
}
