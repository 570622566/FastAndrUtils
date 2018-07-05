package com.hotapk.fastandrutils;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import cn.hotapk.fastandrutils.utils.FPermissionUtils;
import cn.hotapk.fastandrutils.utils.FToastUtils;

public class FPermissionActivity extends FBaseActivity {
    /**
     * @author laijian
     * @version 2017/11/8
     * @Copyright (C)下午2:35 , www.hotapk.cn
     * 权限申请
     */

    FPermissionUtils fPermissionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpermission);
        fPermissionUtils = new FPermissionUtils(this);
        findViewById(R.id.req_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fPermissionUtils.requestPermissions(
                        200, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH}, new FPermissionUtils.OnPermissionListener() {
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
        fPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
