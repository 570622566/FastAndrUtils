package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import cn.hotapk.fastandrutils.utils.FAssetsARawUtils;
import cn.hotapk.fastandrutils.utils.FFileUtils;
import cn.hotapk.fastandrutils.utils.FToastUtils;

/**
 * @author laijian
 * @version 2017/11/2
 * @Copyright (C)下午3:24 , www.hotapk.cn
 * 获取assets的内容
 */
public class FReadAssetsActivity extends FBaseActivity {

    TextView title_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fread_assets);
        title_content = (TextView) findViewById(R.id.title_content);
        String str = FAssetsARawUtils.getAssetsToString("test.txt") + "\n" + FAssetsARawUtils.getRawToString(R.raw.raw);
        title_content.setText(str);

        findViewById(R.id.save_assets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    boolean b = FAssetsARawUtils.assetsDataToSD(FFileUtils.getRootDir() + "/test.txt", "test.txt");

                    if (b) {
                        FToastUtils.init().show("文件已经保存到" + FFileUtils.getRootDir() + "/test.txt");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        findViewById(R.id.save_raw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean b = FAssetsARawUtils.copyRawFileToSdcard(FFileUtils.getRootDir() + "/raw.txt", R.raw.raw);
                if (b) {
                    FToastUtils.init().show("文件已经保存到" + FFileUtils.getRootDir() + "/raw.txt");
                }
            }
        });

    }
}
