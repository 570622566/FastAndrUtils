package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.hotapk.fastandrutils.utils.FShare;

public class FShareActivity extends AppCompatActivity {

    private Button sysshare;
    private Button customshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fshare);
        this.customshare = (Button) findViewById(R.id.custom_share);
        this.sysshare = (Button) findViewById(R.id.sys_share);

        customshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FShare.FShareBuilder(FShareActivity.this, "cn.hotapk.goodfilemanager.fileprovider")
                        .setShareContent("0000")
                        .setShareSubject("dddd")
                        .setShareFilter(new String[]{"com.tencent.mm", "cn.andouya", "com.qihoo360.feichuan", "com.lenovo.anyshare", "com.sand.airdroid"})
                        .build()
                        .shareByCustom();
            }
        });

        sysshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FShare.FShareBuilder(FShareActivity.this, null)
                        .setShareContent("0000")
                        .setShareSubject("dddd")
                        .build()
                        .shareBySystem();
            }
        });


    }
}
