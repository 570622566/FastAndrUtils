package com.hotapk.fastandrutils.fragment;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotapk.fastandrutils.R;

import cn.hotapk.fastandrutils.fragment.FLazyFragment;
import cn.hotapk.fastandrutils.utils.FLogUtils;

/**
 * @author laijian
 * @Copyright (C)2018-06-06 21:04:18 , www.hotapk.cn
 */
public class TabOneFragment extends FLazyFragment {
    private TextView tv_text;

    public TabOneFragment() {
    }

    @Override
    public int setLayoutRes() {
        return R.layout.fragment_tab_one;
    }

    public static TabOneFragment newInstance() {
        TabOneFragment fragment = new TabOneFragment();
        return fragment;
    }

    @Override
    public void initView(View view) {
        FLogUtils.getInstance().e("数据加载1");
        tv_text = view.findViewById(R.id.tv_text);
        tv_text.setText("第一个frame加载数据");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FLogUtils.getInstance().e("onDestroy_one");
    }

    @Override
    public void onPause() {
        super.onPause();
        FLogUtils.getInstance().e("onPause_one");

    }

    @Override
    public void onResume() {
        super.onResume();
        FLogUtils.getInstance().e("onResume_one");

    }

    @Override
    public void onStop() {
        super.onStop();
        FLogUtils.getInstance().e("onStop_one");

    }

    @Override
    public void onStart() {
        super.onStart();
        FLogUtils.getInstance().e("onStart_one");

    }
}
