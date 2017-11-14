package com.hotapk.fastandrutils;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.hotapk.fastandrutils.utils.FLogUtils;
import cn.hotapk.fastandrutils.utils.FPermissionUtils;
import cn.hotapk.fastandrutils.utils.FToastUtils;

public class FPermissionActivity extends FBaseActivity {
    /**
     * @author laijian
     * @version 2017/11/8
     * @Copyright (C)下午2:35 , www.hotapk.cn
     * 权限申请
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpermission);
        findViewById(R.id.req_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FPermissionUtils.requestPermissions(FPermissionActivity.this, 200, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH}, new FPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        FToastUtils.init().show("通过");
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {

                    }

                    //返回manifest没注册的权限，测试的时候比较方便找出问题
                    @Override
                    public void manifestUnPermission(String[] unpermission) {

                        FToastUtils.init().show(unpermission);
                    }
                });
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        FPermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);

    }
}
