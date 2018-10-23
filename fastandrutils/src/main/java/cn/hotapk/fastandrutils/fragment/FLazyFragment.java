package cn.hotapk.fastandrutils.fragment;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.hotapk.fastandrutils.utils.FLogUtils;

/**
 * @author laijian
 * @Copyright (C)2018-06-08 16:33:51 , www.hotapk.cn
 */
public abstract class FLazyFragment extends Fragment {

    private View rootView;
    private boolean needInitData = true;//是否已经初始化数据
    private boolean isInitView = false;//是否初始化view

    public FLazyFragment() {
    }

    public abstract @LayoutRes
    int setLayoutRes();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            isInitView = true;
            rootView = inflater.inflate(setLayoutRes(), container, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        lazyLoad();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    private void lazyLoad() {
        if (getUserVisibleHint() && needInitData && isInitView) {
            needInitData = false;
            if (rootView != null) {
                initView(rootView);
            }
        }
    }

    public abstract void initView(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        rootView = null;
        FLogUtils.getInstance().e("ppp000");
    }
}
