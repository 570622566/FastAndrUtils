package com.hotapk.fastandrutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.hotapk.fastandrutils.bean.TitleInfor;
import com.hotapk.fastandrutils.statusbar.ChangeBarActivity;
import com.hotapk.fastandrutils.statusbar.DrawerBarActivity;
import com.hotapk.fastandrutils.statusbar.MarginTopBarActivity;
import com.hotapk.fastandrutils.statusbar.PaddingTopBarActivity;
import com.hotapk.fastandrutils.statusbar.SysBarActivity;
import com.hotapk.fastandrutils.statusbar.TransBarActivity;

import java.util.ArrayList;
import java.util.List;

import cn.hotapk.fastandrutils.recyclerView.FSimpleRvAdapter;
import cn.hotapk.fastandrutils.recyclerView.FViewHolder;
import cn.hotapk.fastandrutils.utils.FLogUtils;
import cn.hotapk.fastandrutils.utils.FStatusBarUtils;

/**
 * @author laijian
 * @version 2017/11/7
 * @Copyright (C)下午3:58 , www.hotapk.cn
 * 状态栏
 */

public class FStatusBarActivity extends FBaseActivity {

    private RecyclerView recyclerview;
    private List<TitleInfor> titleInfors = new ArrayList<>();
    private FSimpleRvAdapter<TitleInfor> autoRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bur);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        setData();
        setAutoRVAdapter();
        TitleInfor s = new TitleInfor("s");
        FLogUtils.getInstance().e(s);
    }

    private void setData() {
        titleInfors.add(new TitleInfor("状态栏透明"));
        titleInfors.add(new TitleInfor("使用正常的设置系统状态栏颜色"));
        titleInfors.add(new TitleInfor("让标题栏增加paddingTop的值"));
        titleInfors.add(new TitleInfor("伪装个状态栏宽高一样的view填充"));
        titleInfors.add(new TitleInfor("兼容drawerLayout的状态栏"));
        titleInfors.add(new TitleInfor("状态栏的透明度随着标题栏的高度变化"));
        titleInfors.add(new TitleInfor("设置状态栏黑色字体图标"));
        titleInfors.add(new TitleInfor("设置状态栏白色字体图标"));
    }

    private void setAutoRVAdapter() {

        autoRVAdapter = new FSimpleRvAdapter<TitleInfor>(this, titleInfors, R.layout.title_item) {
            @Override
            public void convertHolder(FViewHolder holder, TitleInfor item, int position) {
                holder.setText(R.id.item_tv, item.getTitleName());
            }
        };


        autoRVAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                setOnItemClick(position);
            }
        });
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(autoRVAdapter);
    }


    private void setOnItemClick(int position) {
        Intent intent = null;
        if (position == 0) {
            intent = new Intent(this, TransBarActivity.class);
        } else if (position == 1) {
            intent = new Intent(this, SysBarActivity.class);
        } else if (position == 2) {
            intent = new Intent(this, PaddingTopBarActivity.class);
        } else if (position == 3) {
            intent = new Intent(this, MarginTopBarActivity.class);
        } else if (position == 4) {
            intent = new Intent(this, DrawerBarActivity.class);
        } else if (position == 5) {
            intent = new Intent(this, ChangeBarActivity.class);
        } else if (position == 6) {
            FStatusBarUtils.setStatusBarDarktMode(this);
        } else if (position == 7) {
            FStatusBarUtils.setStatusBarLightMode(this);

        }
        if (position != 6 && position != 7) {
            startActivity(intent);
        }

    }

}