package com.hotapk.fastandrutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.hotapk.fastandrlibs.config.FLogConf;
import com.hotapk.fastandrlibs.config.FToastConf;
import com.hotapk.fastandrlibs.utils.FLogUtils;
import com.hotapk.fastandrlibs.utils.FToastUtils;

public class MainActivity extends AppCompatActivity {
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_toast_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                FToastConf fToastConf = new FToastConf();
                fToastConf.setGrivity(Gravity.TOP);
                fToastConf.setRoundRadius(100);
                FToastUtils.getInstant(MainActivity.this).setConf(fToastConf).showMessage(i + "Dddddddddddddddddddddddddddddddddddcdd");
            }
        });
    }


}
