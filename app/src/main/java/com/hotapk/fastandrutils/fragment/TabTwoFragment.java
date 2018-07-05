package com.hotapk.fastandrutils.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.hotapk.fastandrutils.R;

import cn.hotapk.fastandrutils.fragment.FLazyFragment;
import cn.hotapk.fastandrutils.utils.FLogUtils;
import cn.hotapk.fastandrutils.widget.FEmptyView;

/**
 * @author laijian
 * @Copyright (C)2018-06-06 21:04:18 , www.hotapk.cn
 */
public class TabTwoFragment extends FLazyFragment {
    private TextView tv_text;
    private FEmptyView empty_layout;

    public TabTwoFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static TabTwoFragment newInstance() {
        TabTwoFragment fragment = new TabTwoFragment();
        return fragment;
    }

    @Override
    public int setLayoutRes() {
        return R.layout.fragment_tab_two;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initView(View view) {
        tv_text = view.findViewById(R.id.tv_text);
        empty_layout = view.findViewById(R.id.empty_layout);
        empty_layout.loadding();
        FLogUtils.getInstance().e("第二个frame加载数据");
//        tv_text.setText("第二个frame加载数据");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                empty_layout.dismissEmptyView();
            }
        }, 1000);

    }

}
