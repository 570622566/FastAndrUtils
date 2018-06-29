package cn.hotapk.fastandrutils.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import cn.hotapk.fastandrutils.R;
import cn.hotapk.fastandrutils.utils.FConvertUtils;

/**
 * @author laijian
 * @Copyright (C) 2018-06-28 17:40:31 , www.hotapk.cn
 */
public abstract class FBaseDialogFragment extends DialogFragment {

    protected View mRootView;

    //布局id
    protected int contentView = R.layout.floadding_dialog;

    //透明度
    protected float alpha = 0;

    //是否点击外部关闭
    protected boolean canceledOutside = true;

    //是否可以点击返回键
    protected boolean dialogCancelable = true;

    //显示方向
    protected int gravity = Gravity.CENTER;

    //x偏移量
    protected int offsetX = 0;

    //Y偏移量
    protected int offsetY = 0;

    protected ViewListener viewListener;

    protected DismissListener dismissListener;

    public FBaseDialogFragment() {

    }

    public abstract void showDialog(AppCompatActivity appCompatActivity);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog_translucent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(contentView, container, false);
            getView(mRootView);
            if (viewListener != null) {
                viewListener.getView(mRootView);
            }
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    public abstract void getView(View view);

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), getTheme());
        dialog.setCanceledOnTouchOutside(canceledOutside);
        dialog.setCancelable(dialogCancelable);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = FConvertUtils.dip2px(offsetX);
            attributes.y = FConvertUtils.dip2px(offsetY);
            attributes.gravity = gravity;
            window.setDimAmount(alpha);
        }
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (dismissListener != null) {
            dismissListener.onDismiss();
        }
    }


    public void setContentView(int contentView) {
        this.contentView = contentView;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setCanceledOutside(boolean canceledOutside) {
        this.canceledOutside = canceledOutside;
    }

    public void setDialogCancelable(boolean dialogCancelable) {
        this.dialogCancelable = dialogCancelable;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public void setViewListener(ViewListener viewListener) {
        this.viewListener = viewListener;
    }

    public void setDismissListener(DismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public void setLeftBtLisener(@IdRes int leftId, View.OnClickListener onClickListener) {
        mRootView.findViewById(leftId).setOnClickListener(onClickListener);
    }

    public void setCenterBtLisener(@IdRes int centerId, View.OnClickListener onClickListener) {
        mRootView.findViewById(centerId).setOnClickListener(onClickListener);
    }

    public void setRightBtLisener(@IdRes int rightId, View.OnClickListener onClickListener) {
        mRootView.findViewById(rightId).setOnClickListener(onClickListener);
    }


    interface ViewListener {
        void getView(View v);
    }

    interface DismissListener {
        void onDismiss();
    }


}
