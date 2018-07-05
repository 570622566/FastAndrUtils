package cn.hotapk.fastandrutils.config;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.WindowManager;

import cn.hotapk.fastandrutils.fragment.FBaseDialogFragment;

/**
 * @author laijian
 * @version 2018/6/30
 * @Copyright (C)下午5:56 , www.hotapk.cn
 * @since
 */
public abstract class FBaseDialogBuilder<T extends FBaseDialogBuilder, D extends FBaseDialogFragment> {

    private FragmentActivity appCompatActivity;
    //透明度
    private float alpha = 0;

    //是否点击外部关闭
    private boolean canceledOutside = true;

    //是否可以点击返回键
    private boolean dialogCancelable = true;

    //显示方向
    private int gravity = Gravity.CENTER;

    //x偏移量
    private float offsetX = 0;

    //Y偏移量
    private float offsetY = 0;

    //动画
    private int animationStyle = 0;

    //键盘
    private int softinputmode = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;

    private String dialogTag = "";

    private FBaseDialogFragment.DialogViewListener dialogViewListener;

    private FBaseDialogFragment.DialogDismissListener dialogDismissListener;


    public FBaseDialogBuilder(FragmentActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public T setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        this.alpha = alpha;
        return (T) this;
    }

    public T setCanceledOutside(boolean canceledOutside) {
        this.canceledOutside = canceledOutside;
        return (T) this;
    }

    public T setDialogCancelable(boolean dialogCancelable) {
        this.dialogCancelable = dialogCancelable;
        return (T) this;

    }


    public T setGravity(@FBaseDialogFragment.DialogGravity int gravity) {
        this.gravity = gravity;
        return (T) this;

    }

    public T setAnimationStyle(@StyleRes int animationStyle) {
        this.animationStyle = animationStyle;
        return (T) this;

    }

    public T setOffsetX(float offsetX) {
        this.offsetX = offsetX;
        return (T) this;

    }

    public T setOffsetY(float offsetY) {
        this.offsetY = offsetY;
        return (T) this;

    }

    public T setSoftinputmode(int softinputmode) {
        this.softinputmode = softinputmode;
        return (T) this;

    }

    public T setDialogViewListener(FBaseDialogFragment.DialogViewListener dialogViewListener) {
        this.dialogViewListener = dialogViewListener;
        return (T) this;
    }

    public T setDialogDismissListener(FBaseDialogFragment.DialogDismissListener dialogDismissListener) {
        this.dialogDismissListener = dialogDismissListener;
        return (T) this;
    }

    public T setDialogTag(String dialogTag) {
        this.dialogTag = dialogTag;
        return (T) this;
    }

    protected abstract Class<?> dialogClass();

    protected abstract void setBundle(Bundle bundle);

    protected abstract void setListener(D dialog);

    public D creat() {
        Bundle bundle = new Bundle();
        bundle.putFloat(FBaseDialogFragment.FDIALOG_ALPHA, alpha);
        bundle.getBoolean(FBaseDialogFragment.FDIALOG_CANCELEDOUTSIDE, canceledOutside);
        bundle.getBoolean(FBaseDialogFragment.FDIALOG_DIALOGCANCELABLE, dialogCancelable);
        bundle.putInt(FBaseDialogFragment.FDIALOG_GRAVITY, gravity);
        bundle.putInt(FBaseDialogFragment.FDIALOG_ANIMATIONSTYLE, animationStyle);
        bundle.putFloat(FBaseDialogFragment.FDIALOG_OFFSETX, offsetX);
        bundle.putFloat(FBaseDialogFragment.FDIALOG_OFFSETY, offsetY);
        bundle.putInt(FBaseDialogFragment.FDIALOG_SOFTINPUTMODE, softinputmode);
        bundle.putString(FBaseDialogFragment.FDIALOG_TAG, dialogTag);
        setBundle(bundle);
        D dialog = (D) DialogFragment.instantiate(appCompatActivity, dialogClass().getName(), bundle);
        dialog.setDialogViewListener(dialogViewListener);
        dialog.setDialogDismissListener(dialogDismissListener);
        dialog.setFragmentManager(appCompatActivity.getSupportFragmentManager());
        setListener(dialog);
        return dialog;
    }


}
