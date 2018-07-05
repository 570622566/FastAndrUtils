package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import cn.hotapk.fastandrutils.utils.FAssetsARawUtils;
import cn.hotapk.fastandrutils.utils.FCleanUtils;
import cn.hotapk.fastandrutils.utils.FUtils;


/**
 * @author laijian
 * @version 2017/11/2
 * @Copyright (C)下午3:24 , www.hotapk.cn
 * 清除数据缓存
 */
public class FCleanCacheActivity extends FBaseActivity {
    private TextView content_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fclean_cache);
        content_tv = (TextView) findViewById(R.id.content_tv);
        StringBuffer sb = new StringBuffer();

        try {
            boolean b = FAssetsARawUtils.assetsDataToSD(FUtils.getAppContext().getCacheDir().getAbsolutePath() + "/test.txt", "test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sb.append("app缓存目录：" + getCacheDir().getAbsolutePath());
        sb.append("\napp应用内部缓存否存在：" + new File(FUtils.getAppContext().getCacheDir().getAbsolutePath() + "/test.txt").exists());
        //清除缓存
        FCleanUtils.cleanInternalCache();
        sb.append("\napp应用内部缓存是否还存在：" + new File(FUtils.getAppContext().getCacheDir().getAbsolutePath() + "/test.txt").exists());
        //清除数据库
        FCleanUtils.cleanDatabases();
        content_tv.setText(sb.toString());
//
    }
}
