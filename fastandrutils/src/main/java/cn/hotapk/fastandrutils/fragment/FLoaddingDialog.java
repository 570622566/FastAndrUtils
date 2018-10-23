package cn.hotapk.fastandrutils.fragment;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.view.View;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cn.hotapk.fastandrutils.R;

/**
 * @author laijian
 * @version 2018/6/29
 * @Copyright (C)上午11:22 , www.hotapk.cn
 */
public class FLoaddingDialog extends FBaseDialogFragment {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final String LOADDINGTITLE = "LOADDINGTITLE";
    public static final String LOADDINGORIENTATION = "LOADDINGORIENTATION";
    public TextView textView;

    public FLoaddingDialog() {
    }

    @Override
    public void show(String title) {
        if (textView != null) {
            textView.setText(title);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int setContentView() {
        if (getArguments() != null) {
            if (getArguments().getInt(LOADDINGORIENTATION, VERTICAL) == HORIZONTAL) {
                return R.layout.floadding_dialog_lan;
            }
        }
        return R.layout.floadding_dialog;
    }

    @Override
    public void initView(View view) {
        textView = view.findViewById(R.id.loadding_tv);
        if (getArguments() != null) {
            textView.setText(getArguments().getString(LOADDINGTITLE, "加载中。。。"));
        }
    }

    @IntDef({HORIZONTAL, VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }


}
