package com.hotapk.fastandrutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.hotapk.fastandrlibs.utils.FLogUtils;
import cn.hotapk.fastandrlibs.utils.FNetworkUtils;

public class HttpdActivity extends AppCompatActivity {

    TextView open_bt;
    TextView ipaddr_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpd);
        open_bt = (TextView) findViewById(R.id.open_bt);
        ipaddr_tv = (TextView) findViewById(R.id.ipaddr_tv);
        open_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_bt.setText("关闭");
                FLogUtils.getInstance().startLogServer(8080);
            }
        });
        ipaddr_tv.setText(FNetworkUtils.getIPAddress(true));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FLogUtils.getInstance().stopLogServer();
    }
}
