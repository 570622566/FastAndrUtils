package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.hotapk.fastandrutils.fragment.FLoaddingDialog;
import cn.hotapk.fastandrutils.fragment.FLoaddingDialog.LoaddingBuilder;
import cn.hotapk.fastandrutils.widget.FButton;


/**
 * @author laijian
 * @Copyright (C)2018-06-28 14:34:13 , www.hotapk.cn
 */

public class DialogActivity extends AppCompatActivity {

    private FButton fButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        fButton = findViewById(R.id.dialog_bt);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FLoaddingDialog creat = new LoaddingBuilder()
                        .setTitle("正在加载。。。")
                        .setAlpha(0.2f)
                        .creat();
                creat.showDialog(DialogActivity.this);

            }
        });
    }
}
