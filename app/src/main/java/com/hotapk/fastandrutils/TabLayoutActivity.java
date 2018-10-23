package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.hotapk.fastandrutils.fragment.TabFiveFragment;
import com.hotapk.fastandrutils.fragment.TabFourthFragment;
import com.hotapk.fastandrutils.fragment.TabOneFragment;
import com.hotapk.fastandrutils.fragment.TabThirdFragment;
import com.hotapk.fastandrutils.fragment.TabTwoFragment;

import java.util.ArrayList;
import java.util.List;

import cn.hotapk.fastandrutils.ui.TabLayoutManager;


/**
 * @author laijian
 * @Copyright (C)2018-06-06 20:54:44 , www.hotapk.cn
 */

public class TabLayoutActivity extends FBaseActivity {
    private TextView add_bt;
    private TextView del_bt;
    private TextView change_bt;
    private TabLayout tabLayout;
    private TabLayoutManager tabLayoutManager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<Integer> tabNorImgs = new ArrayList<>();
    private List<Integer> tabSelImgs = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        tabLayout = findViewById(R.id.tab_lay);
        add_bt = findViewById(R.id.add_bt);
        del_bt = findViewById(R.id.del_bt);
        fragments.add(TabOneFragment.newInstance());
        fragments.add(TabTwoFragment.newInstance());
        fragments.add(TabThirdFragment.newInstance());
        fragments.add(TabFourthFragment.newInstance());

        tabNorImgs.add(R.mipmap.home_nor);
        tabNorImgs.add(R.mipmap.flei_nor);
        tabNorImgs.add(R.mipmap.icon_video_nor);
        tabNorImgs.add(R.mipmap.me_nor);

        tabSelImgs.add(R.mipmap.home_sel);
        tabSelImgs.add(R.mipmap.flei_sel);
        tabSelImgs.add(R.mipmap.icon_video_sel);
        tabSelImgs.add(R.mipmap.me_sel);

        titles.add("第一");
        titles.add("第二");
        titles.add("第三");
        titles.add("第四");

        tabLayoutManager = new TabLayoutManager(this, tabLayout)
                .setFrames(fragments)
//                .setFrameIdRes(R.id.frame)
                .setItemTabRes(R.layout.tab_item)
                .setImgIdRes(R.id.tab_img)
                .setTViewIdRes(R.id.tab_tv)
                .setTitleNorColor(R.color.nor_tv)
                .setTitleSelColor(R.color.sel_tv)
                .setImgNorDrawable(tabNorImgs)
                .setImgSelDrawable(tabSelImgs)
                .setTitles(titles);
        tabLayoutManager.creatPagerTab((ViewPager) findViewById(R.id.pager_lay));

        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLayoutManager.addFrames(TabFiveFragment.newInstance());
                tabLayoutManager.addImgNorDrawable(R.mipmap.home_nor);
                tabLayoutManager.addImgSelDrawable(R.mipmap.home_sel);
                tabLayoutManager.addTitles("第五");
                tabLayoutManager.addTab();
                tabLayoutManager.adapterNotify();
            }
        });
        del_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLayoutManager.removeTab(0);
                tabLayoutManager.adapterNotify();

            }
        });
    }
}
