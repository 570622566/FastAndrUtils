package cn.hotapk.fastandrutils.fragment;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import cn.hotapk.fastandrutils.R;

/**
 * @author laijian
 * @version 2018/6/29
 * @Copyright (C)上午11:22 , www.hotapk.cn
 */
public class FLoaddingDialog extends FBaseDialogFragment {

    private boolean isvertical = true;
    private String title = "加载中。。。";


    public FLoaddingDialog() {
        // Required empty public constructor
    }

    public static FLoaddingDialog newInstance() {
        FLoaddingDialog fragment = new FLoaddingDialog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isvertical) {
            contentView = R.layout.floadding_dialog;
        } else {
            contentView = R.layout.floadding_dialog_lan;
        }
    }


    @Override
    public void showDialog(AppCompatActivity appCompatActivity) {
        this.show(appCompatActivity.getSupportFragmentManager(), "loadding");
    }

    @Override
    public void getView(View view) {
        ((TextView) view.findViewById(R.id.loadding_tv)).setText(title);
    }


    public void setIsvertical(boolean isvertical) {
        this.isvertical = isvertical;

    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static class LoaddingBuilder {
        private boolean isvertical = true;
        private String title = "加载中。。。";
        private float alpha;
        //是否点击外部关闭
        private boolean canceledOutside = true;
        //是否可以点击返回键
        private boolean dialogCancelable = true;

        public LoaddingBuilder() {

        }

        public LoaddingBuilder setIsvertical(boolean isvertical) {
            this.isvertical = isvertical;
            return this;
        }

        public LoaddingBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public LoaddingBuilder setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
            this.alpha = alpha;
            return this;
        }

        public LoaddingBuilder setCanceledOutside(boolean canceledOutside) {
            this.canceledOutside = canceledOutside;
            return this;
        }

        public LoaddingBuilder setDialogCancelable(boolean dialogCancelable) {
            this.dialogCancelable = dialogCancelable;
            return this;
        }

        public FLoaddingDialog creat() {
            FLoaddingDialog fLoaddingDialog = newInstance();
            fLoaddingDialog.setTitle(title);
            fLoaddingDialog.setIsvertical(isvertical);
            fLoaddingDialog.setAlpha(alpha);
            fLoaddingDialog.setCanceledOutside(canceledOutside);
            fLoaddingDialog.setDialogCancelable(dialogCancelable);
            return fLoaddingDialog;
        }
    }

}
