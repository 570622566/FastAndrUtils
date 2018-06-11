package cn.hotapk.fastandrutils.ui;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import cn.hotapk.fastandrutils.utils.FLogUtils;

/**
 * @author laijian
 * @version 2018/4/29
 * @Copyright (C)上午12:16 , www.hotapk.cn
 * @since
 */
public class TabLayoutManager {

    private OnTabItemSelectedListener onTabItemSelectedListener;
    private AppCompatActivity activity;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private String[] titles;
    private int[] resNors;
    private int[] resSels;
    private int frameIdRes = -1;
    private int layoutRes = -1;
    private int imgResid = -1;
    private int textResid = -1;
    private int colorNor = -1;
    private int colorSel = -1;
    private int tabPosition;
    private Fragment tempFragment;
    private TabFragmentAdapter tabFragmentAdapter;
    private boolean isViewPagerSel = false;
    /**
     * @param activity
     * @param tabLayout
     */
    public TabLayoutManager(AppCompatActivity activity, TabLayout tabLayout) {
        this.activity = activity;
        this.tabLayout = tabLayout;
    }

    /**
     * 添加fragment
     *
     * @param fragments
     * @return
     */
    public TabLayoutManager setFrames(List<Fragment> fragments) {
        this.fragments = fragments;
        return this;
    }

    /**
     * 设置frameLayout 的id
     *
     * @param frameIdRes
     * @return
     */
    public TabLayoutManager setFrameIdRes(@IdRes int frameIdRes) {
        this.frameIdRes = frameIdRes;
        return this;
    }


    /**
     * 设置tab item的布局
     *
     * @param layoutRes
     * @return
     */
    public TabLayoutManager setItemTabRes(@LayoutRes int layoutRes) {
        this.layoutRes = layoutRes;
        return this;
    }

    /**
     * 设置item 的图片 id
     *
     * @param imgResid
     * @return
     */
    public TabLayoutManager setImgIdRes(@IdRes int imgResid) {
        this.imgResid = imgResid;
        return this;
    }

    /**
     * 设置item 的text id
     *
     * @param textResid
     * @return
     */
    public TabLayoutManager setTViewIdRes(@IdRes int textResid) {
        this.textResid = textResid;
        return this;
    }

    /**
     * 添加标题
     *
     * @param titles
     * @return
     */
    public TabLayoutManager setTitles(String[] titles) {
        this.titles = titles;
        return this;
    }

    /**
     * 默认字体颜色
     *
     * @param colorNor
     * @return
     */
    public TabLayoutManager setTitleNorColor(@ColorRes int colorNor) {
        this.colorNor = colorNor;
        return this;
    }

    /**
     * 选中字体颜色
     *
     * @param colorSel
     * @return
     */
    public TabLayoutManager setTitleSelColor(@ColorRes int colorSel) {
        this.colorSel = colorSel;
        return this;
    }


    /**
     * 设置默认图片
     *
     * @param resNors
     * @return
     */
    public TabLayoutManager setImgNorDrawable(@DrawableRes int[] resNors) {
        this.resNors = resNors;
        return this;
    }

    /**
     * 设置选中图片
     *
     * @param resSels
     * @return
     */
    public TabLayoutManager setImgSelDrawable(@DrawableRes int[] resSels) {
        this.resSels = resSels;
        return this;
    }


    public void creat() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                setViewChange(tabPosition, false);

                onTabItemSelected(tab.getPosition());

                if (onTabItemSelectedListener != null) {
                    onTabItemSelectedListener.tabItemSelect(tab.getPosition(), tabLayout.getTabAt(tab.getPosition()).getCustomView());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // 提供自定义的布局添加Tab
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(activity.getBaseContext(), i)));
        }
    }

    public void creatPagerTab(final ViewPager viewPager) {
        tabFragmentAdapter = new TabFragmentAdapter(activity.getSupportFragmentManager());
        viewPager.setAdapter(tabFragmentAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (!isViewPagerSel) {
                viewPager.setCurrentItem(tab.getPosition(),false);
                }
                isViewPagerSel = false;
                setViewChange(tabPosition, false);
                if (onTabItemSelectedListener != null) {
                    onTabItemSelectedListener.tabItemSelect(tab.getPosition(), tabLayout.getTabAt(tab.getPosition()).getCustomView());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                isViewPagerSel = true;
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 提供自定义的布局添加Tab
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(activity.getBaseContext(), i)));
        }



    }


    private void setViewChange(int position, boolean sel) {

        ImageView tabIcon = null;
        TextView tabText = null;
        View view = null;
        view = tabLayout.getTabAt(position).getCustomView();
        if (imgResid != -1) {
            tabIcon = view.findViewById(imgResid);
        }
        if (textResid != -1) {
            tabText = view.findViewById(textResid);
        }
        if (!sel) {
            if (tabIcon != null) {
                tabIcon.setImageResource(resNors[position]);
            }
            if (tabText != null) {
                tabText.setTextColor(activity.getResources().getColor(colorNor));
            }
            tabPosition = tabLayout.getSelectedTabPosition();
            setViewChange(tabPosition, true);
        } else {
            if (tabIcon != null) {
                tabIcon.setImageResource(resSels[position]);
            }
            if (tabText != null) {
                tabText.setTextColor(activity.getResources().getColor(colorSel));
            }
        }

    }

    private void onTabItemSelected(int position) {
        Fragment fragment = fragments.get(position);
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (tempFragment != null) {
            fragmentTransaction.hide(tempFragment);
            tempFragment.onPause();
        }
        if (fragment != null) {
            if (fragment.isAdded()) {
                fragmentTransaction.show(fragment);
                fragment.onResume();
            } else {
                fragmentTransaction.add(frameIdRes, fragment);
            }
            fragmentTransaction.commit();
            tempFragment = fragment;
        }
    }

    public void setOnTabItemSelectedListener(OnTabItemSelectedListener onTabItemSelectedListener) {
        this.onTabItemSelectedListener = onTabItemSelectedListener;
    }

    private View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(layoutRes, null);
        if (imgResid != -1) {
            ImageView tabIcon = view.findViewById(imgResid);
            tabIcon.setImageResource(resNors[position]);
        }
        if (textResid != -1) {
            TextView tabText = view.findViewById(textResid);
            tabText.setText(titles[position]);
        }
        return view;
    }

    public interface OnTabItemSelectedListener {
        void tabItemSelect(int position, View selView);
    }

    class TabFragmentAdapter extends FragmentPagerAdapter {

        public TabFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }



        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
