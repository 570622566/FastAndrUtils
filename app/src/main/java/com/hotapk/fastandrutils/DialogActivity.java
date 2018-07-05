package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.hotapk.fastandrutils.bean.TitleInfor;

import java.util.ArrayList;
import java.util.List;

import cn.hotapk.fastandrutils.config.FAlertDialogBuilder;
import cn.hotapk.fastandrutils.config.FLoaddingBuilder;
import cn.hotapk.fastandrutils.fragment.FAlertDialog;
import cn.hotapk.fastandrutils.fragment.FLoaddingDialog;
import cn.hotapk.fastandrutils.recyclerView.FSimpleRvAdapter;
import cn.hotapk.fastandrutils.recyclerView.FViewHolder;
import cn.hotapk.fastandrutils.utils.FToastUtils;


/**
 * @author laijian
 * @Copyright (C)2018-06-28 14:34:13 , www.hotapk.cn
 */

public class DialogActivity extends FBaseActivity {

    private RecyclerView recyclerView;
    private FSimpleRvAdapter<TitleInfor> autoRVAdapter;
    private List<TitleInfor> titleInfors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        titleInfors.add(new TitleInfor("加载窗"));
        titleInfors.add(new TitleInfor("对话窗"));
        autoRVAdapter = new FSimpleRvAdapter<TitleInfor>(this, titleInfors, R.layout.title_item) {
            @Override
            public void convertHolder(FViewHolder holder, TitleInfor item, int position) {
                holder.setText(R.id.item_tv, item.getTitleName());
            }
        };


        autoRVAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    FLoaddingDialog creat = new FLoaddingBuilder(DialogActivity.this)
                            .setTitle("加载中。。。")
                            .setAlpha(0.4f)
                            .setOrientationMode(FLoaddingDialog.VERTICAL).creat();
                    creat.show();
                } else if (position == 1) {
                    FAlertDialog fAlertDialog = new FAlertDialogBuilder(DialogActivity.this)
                            .setContentText("检测到新版本，是否升级？")
                            .setAlpha(0.4f)
                            .setLeftText("ddddd")
                            .setOnLeftClickListener(new FAlertDialog.OnLeftClickListener() {
                                @Override
                                public void onClick(FAlertDialog fAlertDialog) {
                                    FToastUtils.init().show("取消了");
                                }
                            }).setOnRightClickListener(new FAlertDialog.OnRightClickListener() {
                                @Override
                                public void onClick(FAlertDialog fAlertDialog) {
                                    FToastUtils.init().show("确定了");
                                    fAlertDialog.dismiss();
                                }
                            }).creat();
                    fAlertDialog.show();
                }
            }
        });
        recyclerView.setAdapter(autoRVAdapter);

    }
}
