package cn.hotapk.fastandrutils.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author laijian
 * @version 2018/6/8
 * @Copyright (C)下午4:47 , www.hotapk.cn
 */
public abstract class FBaseFragment extends Fragment {

    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(setLayoutRes(), container, false);
            initView(mRootView, savedInstanceState);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    public abstract int setLayoutRes();

    public abstract void initView(View rootView, Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRootView = null;
    }
}
