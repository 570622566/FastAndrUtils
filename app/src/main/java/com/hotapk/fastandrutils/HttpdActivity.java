package com.hotapk.fastandrutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hotapk.fastandrlibs.utils.FLogUtils;
import com.hotapk.fastandrlibs.utils.FLogNetServer;
import com.hotapk.fastandrlibs.utils.FNetworkUtils;

public class HttpdActivity extends AppCompatActivity {

    TextView open_bt;
    FLogNetServer testHttpd;
    TextView ipaddr_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpd);
        testHttpd = new FLogNetServer(8080,this);
        open_bt = (TextView) findViewById(R.id.open_bt);
        ipaddr_tv = (TextView) findViewById(R.id.ipaddr_tv);
        open_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_bt.setText("关闭");
                FLogUtils.getInstance().startLogServer(8080,HttpdActivity.this);
//                try {
//                    testHttpd.start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
        ipaddr_tv.setText(FNetworkUtils.getIPAddress(true));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (testHttpd != null) {
            testHttpd.stop();
        }
    }
}
