package com.hotapk.fastandrutils;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.hotapk.fastandrutils.bean.TitleInfor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.hotapk.fastandrutils.recyclerView.FSimpleRvAdapter;
import cn.hotapk.fastandrutils.recyclerView.FViewHolder;
import cn.hotapk.fastandrutils.utils.FFileUtils;
import cn.hotapk.fastandrutils.utils.FLogUtils;
import cn.hotapk.fastandrutils.utils.FPermissionUtils;

/**
 * @author laijian
 * @version 2017/11/2
 * @Copyright (C)下午3:24 , www.hotapk.cn
 */
public class FMainActivity extends FBaseActivity {

    private RecyclerView recyclerview;
    private List<TitleInfor> titleInfors = new ArrayList<>();
    private FSimpleRvAdapter autoRVAdapter;
    private FPermissionUtils fPermissionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        setData();
        setAutoRVAdapter();
        fPermissionUtils = new FPermissionUtils(this);

        fPermissionUtils.requestPermissions(200, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE}, new FPermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(String[] deniedPermissions) {

            }

            @Override
            public void manifestUnPermission(String[] unpermission) {

            }
        });
        FLogUtils.getInstance().e(FFileUtils.storageToal(new File(FFileUtils.getRootDir())));
        FLogUtils.getInstance().e(FFileUtils.storageUse(new File(FFileUtils.getRootDir())));

    }

    private void setData() {
        titleInfors.add(new TitleInfor("获取app应用相关信息"));
        titleInfors.add(new TitleInfor("获取assets的内容"));
        titleInfors.add(new TitleInfor("清除app缓存"));
        titleInfors.add(new TitleInfor("防止过快点击"));
        titleInfors.add(new TitleInfor("数据转换类"));
        titleInfors.add(new TitleInfor("设备信息类"));
        titleInfors.add(new TitleInfor("Toast显示"));
        titleInfors.add(new TitleInfor("软键盘结合EditText"));
        titleInfors.add(new TitleInfor("沉浸式状态栏"));
        titleInfors.add(new TitleInfor("权限申请"));
        titleInfors.add(new TitleInfor("log日志"));
        titleInfors.add(new TitleInfor("异常退出crash"));
        titleInfors.add(new TitleInfor("时间操作"));
        titleInfors.add(new TitleInfor("简单数据校验相关"));
        titleInfors.add(new TitleInfor("图片相关"));
        titleInfors.add(new TitleInfor("网络相关"));
        titleInfors.add(new TitleInfor("空界面"));
        titleInfors.add(new TitleInfor("弹窗"));
        titleInfors.add(new TitleInfor("Tablayout"));
        titleInfors.add(new TitleInfor("自定义linearlayout"));
        titleInfors.add(new TitleInfor("自定义button"));
        titleInfors.add(new TitleInfor("自定义Dialog"));
        titleInfors.add(new TitleInfor("系统分享"));
    }

    private void setAutoRVAdapter() {

        autoRVAdapter = new FSimpleRvAdapter<TitleInfor>(this, titleInfors, R.layout.title_item) {
            @Override
            public void convertHolder(FViewHolder holder, TitleInfor item, int position) {
                holder.setText(R.id.item_tv, item.getTitleName());
            }
        };
//
//        autoRVAdapter.addHeader(R.layout.title2_item, new FBaseRvAdapter.HeaderConvert() {
//            @Override
//            public void headerConvert(FViewHolder holder, int position) {
//                holder.setText(R.id.item_tv, "头部一");
//            }
//        });
//        autoRVAdapter.addFooter(R.layout.title2_item, new FBaseRvAdapter.FooterConvert() {
//            @Override
//            public void footerConvert(FViewHolder holder, int position) {
//                holder.setText(R.id.item_tv, "尾部");
//            }
//        });

        autoRVAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setOnItemClick(position);
            }
        });

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(autoRVAdapter);
    }


    private void setOnItemClick(int position) {
        Intent intent = null;
        if (position == 0) {
            intent = new Intent(this, FAppInforActivity.class);
        } else if (position == 1) {
            intent = new Intent(this, FReadAssetsActivity.class);
        } else if (position == 2) {
            intent = new Intent(this, FCleanCacheActivity.class);
        } else if (position == 3) {
            intent = new Intent(this, FCheckDoubleClickActivity.class);
        } else if (position == 4) {
            intent = new Intent(this, FConvertActivity.class);
        } else if (position == 5) {
            intent = new Intent(this, FDeviceActivity.class);
        } else if (position == 6) {
            intent = new Intent(this, FToastActivity.class);
        } else if (position == 7) {
            intent = new Intent(this, FKeyBoradActivity.class);
        } else if (position == 8) {
            intent = new Intent(this, FStatusBarActivity.class);
        } else if (position == 9) {
            intent = new Intent(this, FPermissionActivity.class);
        } else if (position == 10) {
            intent = new Intent(this, FLogActivity.class);
        } else if (position == 11) {
        } else if (position == 12) {
            intent = new Intent(this, FDateActivity.class);
        } else if (position == 13) {
            intent = new Intent(this, FValidatorActivity.class);
        } else if (position == 14) {
            intent = new Intent(this, FImageActivity.class);
        } else if (position == 15) {
            intent = new Intent(this, FNetworkActivity.class);
        } else if (position == 16) {
            intent = new Intent(this, FEmptyActivity.class);
        } else if (position == 17) {
            intent = new Intent(this, FDialogFragmentActivity.class);
        } else if (position == 18) {
            intent = new Intent(this, TabLayoutActivity.class);
        } else if (position == 19) {
            intent = new Intent(this, LinearLayoutActivity.class);
        } else if (position == 20) {
            intent = new Intent(this, FButtonActivity.class);
        } else if (position == 21) {
            intent = new Intent(this, DialogActivity.class);
        } else if (position == 22) {
            intent = new Intent(this, FShareActivity.class);
        }
        startActivity(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        fPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
