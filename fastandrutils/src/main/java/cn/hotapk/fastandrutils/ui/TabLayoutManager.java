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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laijian
 * @version 2018/4/29
 * @Copyright (C)上午12:16 , www.hotapk.cn
 * @since
 */
public class TabLayoutManager {

    private OnTabItemSelectedListener onTabItemSelectedListener;
    private OnTabFragmetnSelectedListener onTabFragmetnSelectedListener;
    private AppCompatActivity activity;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<Integer> resNors = new ArrayList<>();
    private List<Integer> resSels = new ArrayList<>();
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
     * 添加fragment
     *
     * @param fragment
     * @return
     */
    public TabLayoutManager addFrames(Fragment fragment) {
        this.fragments.add(fragment);
        return this;
    }

    public TabLayoutManager changeFrames(int position, Fragment fragment) {
        fragments.set(position, fragment);
        return this;
    }


    /**
     * 添加标题
     *
     * @param titles
     * @return
     */
    public TabLayoutManager setTitles(List<String> titles) {
        this.titles.addAll(titles);
        return this;
    }

    /**
     * 添加标题
     *
     * @param title
     * @return
     */
    public TabLayoutManager addTitles(String title) {
        this.titles.add(title);
        return this;
    }

    public TabLayoutManager changeTitles(int position, String title) {
        this.titles.set(position, title);
        return this;
    }


    /**
     * 设置默认图片
     *
     * @param resNors
     * @return
     */
    public TabLayoutManager setImgNorDrawable(@DrawableRes List<Integer> resNors) {
        this.resNors.addAll(resNors);
        return this;
    }

    /**
     * 设置默认图片
     *
     * @param resNor
     * @return
     */
    public TabLayoutManager addImgNorDrawable(@DrawableRes int resNor) {
        this.resNors.add(resNor);
        return this;
    }

    public TabLayoutManager changeImgNorDrawable(int position, @DrawableRes int resNor) {
        this.resNors.set(position, resNor);
        return this;
    }


    /**
     * 设置选中图片
     *
     * @param resSels
     * @return
     */
    public TabLayoutManager setImgSelDrawable(@DrawableRes List<Integer> resSels) {
        this.resSels.addAll(resSels);
        return this;
    }

    /**
     * 设置选中图片
     *
     * @param resSel
     * @return
     */
    public TabLayoutManager addImgSelDrawable(@DrawableRes int resSel) {
        this.resSels.add(resSel);
        return this;
    }

    public TabLayoutManager changeImgSelDrawable(int position, @DrawableRes int resSel) {
        this.resSels.set(position, resSel);
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


    public void addTab() {
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(activity.getBaseContext(), titles.size() - 1)));
    }

    public void changeTab(int position) {
        tabLayout.getTabAt(position).setCustomView(getTabView(activity.getBaseContext(), position));
    }

    public void removeTab(int position) {
        fragments.remove(position);
        titles.remove(position);
        resNors.remove(position);
        resSels.remove(position);
        tabLayout.removeTabAt(position);
        tabPosition = tabLayout.getSelectedTabPosition();

    }

    public void changeTabFrame(int postion, Fragment fragment) {
        fragments.set(postion, fragment);
        changeTab(postion);
    }


    public void creat() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (onTabFragmetnSelectedListener == null) {
                    onTabItemSelected(tab.getPosition());
                } else {
                    onTabFragmetnSelectedListener.tabItemSelect(tabPosition, tab.getPosition());
                }
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
        // 提供自定义的布局添加Tab
        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(activity.getBaseContext(), i)));
        }
    }

    public void adapterNotify() {
        if (tabFragmentAdapter != null) {
            tabFragmentAdapter.notifyDataSetChanged();
        }
    }

    public void creatPagerTab(final ViewPager viewPager) {
        tabFragmentAdapter = new TabFragmentAdapter(activity.getSupportFragmentManager());
        viewPager.setAdapter(tabFragmentAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (!isViewPagerSel) {
                    viewPager.setCurrentItem(tab.getPosition(), false);
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
        for (int i = 0; i < titles.size(); i++) {
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
                tabIcon.setImageResource(resNors.get(position));
            }
            if (tabText != null) {
                tabText.setTextColor(activity.getResources().getColor(colorNor));
            }
            tabPosition = tabLayout.getSelectedTabPosition();
            setViewChange(tabPosition, true);
        } else {
            if (tabIcon != null) {
                tabIcon.setImageResource(resSels.get(position));
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

    public void setOnTabFragmetnSelectedListener(OnTabFragmetnSelectedListener onTabFragmetnSelectedListener) {
        this.onTabFragmetnSelectedListener = onTabFragmetnSelectedListener;
    }

    private View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(layoutRes, null);
        if (imgResid != -1) {
            ImageView tabIcon = view.findViewById(imgResid);
            tabIcon.setImageResource(resNors.get(position));
        }
        if (textResid != -1) {
            TextView tabText = view.findViewById(textResid);
            tabText.setText(titles.get(position));
        }
        return view;
    }


    public interface OnTabItemSelectedListener {
        void tabItemSelect(int position, View selView);
    }

    public interface OnTabFragmetnSelectedListener {
        void tabItemSelect(int oldPosition, int position);
    }


    class TabFragmentAdapter extends FragmentPagerAdapter {

        public TabFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            System.out.println("000----" + position);
            return fragments.get(position);
        }


        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
