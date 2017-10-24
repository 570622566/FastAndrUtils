package com.hotapk.fastandrutils;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import cn.hotapk.fastandrutils.config.FToastConf;
import cn.hotapk.fastandrutils.utils.FLogUtils;
import cn.hotapk.fastandrutils.utils.FManifestUtils;
import cn.hotapk.fastandrutils.utils.FPermissionUtils;
import cn.hotapk.fastandrutils.utils.FToastUtils;


public class MainActivity extends AppCompatActivity implements FPermissionUtils.OnPermissionListener {
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
                FToastUtils.getInstant().setConf(fToastConf).showMessage(i + "Dddddddddddddddddddddddddddddddddddcdd");
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
        findViewById(R.id.show_emptyview_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EmptyViewActivity.class));
            }
        });

        FPermissionUtils.requestPermissions(this, 200, new String[]{
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, this);

//        FLogUtils.getInstance().e(FManifestUtils.getActivityMetaData("d", MainActivity.class));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FLogUtils.getInstance().e("MainActivity====>onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        FLogUtils.getInstance().e("MainActivity====>onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        FLogUtils.getInstance().e("MainActivity====>onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        FLogUtils.getInstance().e("MainActivity====>onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FLogUtils.getInstance().e("MainActivity====>onDestroy");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        FPermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted() {
    }

    @Override
    public void onPermissionDenied(String[] deniedPermissions) {

    }

    @Override
    public void manifestUnPermission(String[] unpermission) {

    }
}
