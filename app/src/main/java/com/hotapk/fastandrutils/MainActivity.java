package com.hotapk.fastandrutils;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.hotapk.fastandrlibs.config.FToastConf;
import com.hotapk.fastandrlibs.utils.FLogUtils;
import com.hotapk.fastandrlibs.utils.FToastUtils;

import kr.co.namee.permissiongen.PermissionGen;

public class MainActivity extends AppCompatActivity {
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FLogUtils.getInstance().saveSD(true);
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
        findViewById(R.id.show_net_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HttpdActivity.class));
            }
        });
        findViewById(R.id.show_keyboard_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KeyBoardActivity.class));
            }
        });




        PermissionGen.needPermission(this, 200, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
        });

        FLogUtils.getInstance().e("ddd");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);

    }
}
