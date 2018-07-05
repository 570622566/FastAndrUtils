package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hotapk.fastandrutils.fragment.TabFiveFragment;
import com.hotapk.fastandrutils.fragment.TabFourthFragment;
import com.hotapk.fastandrutils.fragment.TabOneFragment;
import com.hotapk.fastandrutils.fragment.TabThirdFragment;
import com.hotapk.fastandrutils.fragment.TabTwoFragment;
import com.hotapk.fastandrutils.fragment.TabsixFragment;

import java.util.ArrayList;
import java.util.List;

import cn.hotapk.fastandrutils.ui.TabLayoutManager;


/**
 * @author laijian
 * @Copyright (C)2018-06-06 20:54:44 , www.hotapk.cn
 */

public class TabLayoutActivity extends FBaseActivity {

    private TabLayout tabLayout;
    private TabLayoutManager tabLayoutManager;
    private List<Fragment> fragments = new ArrayList<>();
    private int[] tabNorImgs = new int[]{R.mipmap.home_nor, R.mipmap.flei_nor, R.mipmap.icon_video_nor, R.mipmap.me_nor, R.mipmap.icon_video_nor, R.mipmap.me_nor, R.mipmap.me_nor, R.mipmap.icon_video_nor, R.mipmap.me_nor, R.mipmap.me_nor, R.mipmap.icon_video_nor, R.mipmap.me_nor, R.mipmap.me_nor, R.mipmap.icon_video_nor, R.mipmap.me_nor};
    private int[] tabSelImgs = new int[]{R.mipmap.home_sel, R.mipmap.flei_sel, R.mipmap.icon_video_sel, R.mipmap.me_sel, R.mipmap.icon_video_sel, R.mipmap.me_sel, R.mipmap.me_sel, R.mipmap.icon_video_sel, R.mipmap.me_sel, R.mipmap.me_sel, R.mipmap.icon_video_sel, R.mipmap.me_sel, R.mipmap.me_sel, R.mipmap.icon_video_sel, R.mipmap.me_sel};
    private String[] titles = new String[]{"第一", "第二", "第三", "第四", "第五", "第六", "第四", "第五", "第六", "第四", "第五", "第六", "第四", "第五", "第六"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        tabLayout = findViewById(R.id.tab_lay);
        fragments.add(TabOneFragment.newInstance());
        fragments.add(TabTwoFragment.newInstance());
        fragments.add(TabThirdFragment.newInstance());
        fragments.add(TabFourthFragment.newInstance());
        fragments.add(TabFiveFragment.newInstance());
        fragments.add(TabsixFragment.newInstance());
        fragments.add(TabFourthFragment.newInstance());
        fragments.add(TabFiveFragment.newInstance());
        fragments.add(TabsixFragment.newInstance());
        fragments.add(TabFourthFragment.newInstance());
        fragments.add(TabFiveFragment.newInstance());
        fragments.add(TabsixFragment.newInstance());
        fragments.add(TabFourthFragment.newInstance());
        fragments.add(TabFiveFragment.newInstance());
        fragments.add(TabsixFragment.newInstance());


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
    }
}
