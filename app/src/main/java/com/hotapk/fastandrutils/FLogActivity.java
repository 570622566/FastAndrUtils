package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hotapk.fastandrutils.bean.TitleInfor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hotapk.fastandrutils.utils.FLogUtils;
import cn.hotapk.fastandrutils.utils.FNetworkUtils;
import cn.hotapk.fastandrutils.utils.FToastUtils;

/**
 * @author laijian
 * @version 2017/11/9
 * @Copyright (C)上午10:15 , www.hotapk.cn
 * log日志类
 */
public class FLogActivity extends FBaseActivity {
    private TextView network_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flog);

        //默认是不存到sd卡的
        FLogUtils.getInstance().saveSD(true);
        //使用
        FLogUtils.getInstance().e("这样子使用");

        //对象的打印也简单
        TitleInfor titleInfor = new TitleInfor("对象的打印以json格式输出");
        FLogUtils.getInstance().e(titleInfor);

        //map的打印
        Map<String, String> map = new HashMap<>();
        map.put("map的打印", "json输出");
        FLogUtils.getInstance().e(map);

        //list打印
        List<TitleInfor> titleInfors = new ArrayList<>();
        TitleInfor titleInfor2 = new TitleInfor("list的打印以json格式输出");
        titleInfors.add(titleInfor2);
        FLogUtils.getInstance().e(titleInfors);


        network_tv = (TextView) findViewById(R.id.network_tv);
        findViewById(R.id.log_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int port = 9090;
                FLogUtils.getInstance().startLogServer(port);
                FToastUtils.init().show("已打开log内网服务");
                network_tv.setText("请访问：http://" + FNetworkUtils.getIPAddress() + ":" + port);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FLogUtils.getInstance().stopLogServer();
    }
}
