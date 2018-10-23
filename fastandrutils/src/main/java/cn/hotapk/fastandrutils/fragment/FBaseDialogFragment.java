package cn.hotapk.fastandrutils.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cn.hotapk.fastandrutils.R;
import cn.hotapk.fastandrutils.utils.FConvertUtils;

/**
 * @author laijian
 * @Copyright (C) 2018-06-28 17:40:31 , www.hotapk.cn
 */
public abstract class FBaseDialogFragment extends DialogFragment {

    //透明度
    public static final String FDIALOG_ALPHA = "FDIALOG_ALPHA";
    //是否点击外部关闭
    public static final String FDIALOG_CANCELEDOUTSIDE = "FDIALOG_CANCELEDOUTSIDE";
    //是否可以点击返回键
    public static final String FDIALOG_DIALOGCANCELABLE = "FDIALOG_DIALOGCANCELABLE";
    //显示方向
    public static final String FDIALOG_GRAVITY = "FDIALOG_GRAVITY";
    //x偏移量
    public static final String FDIALOG_OFFSETX = "FDIALOG_OFFSETX";
    //Y偏移量
    public static final String FDIALOG_OFFSETY = "FDIALOG_OFFSETY";
    //动画
    public static final String FDIALOG_ANIMATIONSTYLE = "FDIALOG_ANIMATIONSTYLE";
    //键盘
    public static final String FDIALOG_SOFTINPUTMODE = "FDIALOG_SOFTINPUTMODE";
    //tag
    public static final String FDIALOG_TAG = "FDIALOG_TAG";
    protected View mRootView;
    private float alpha = 0;

    private boolean canceledOutside = true;

    private boolean dialogCancelable = true;

    private int gravity = Gravity.CENTER;

    private float offsetX = 0;

    private float offsetY = 0;

    private int animationStyle = 0;

    private int softinputmode = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;

    private String dialogTag = "";
    private DialogViewListener dialogViewListener;
    private DialogDismissListener dialogDismissListener;
    private FragmentManager fragmentManager;

    public FBaseDialogFragment() {

    }

    public void show() {
        this.show(fragmentManager, dialogTag);
    }

    public void show(String title) {
        this.show(fragmentManager, dialogTag);
    }

    @LayoutRes
    protected abstract int setContentView();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.fdialog);
        Bundle arguments = getArguments();
        if (arguments != null) {
            canceledOutside = arguments.getBoolean(FDIALOG_CANCELEDOUTSIDE, true);
            dialogCancelable = arguments.getBoolean(FDIALOG_DIALOGCANCELABLE, true);
            alpha = arguments.getFloat(FDIALOG_ALPHA, 0f);
            offsetX = arguments.getFloat(FDIALOG_OFFSETX, 0f);
            offsetY = arguments.getFloat(FDIALOG_OFFSETY, 0f);
            gravity = arguments.getInt(FDIALOG_GRAVITY, Gravity.CENTER);
            softinputmode = arguments.getInt(FDIALOG_SOFTINPUTMODE, WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
            animationStyle = arguments.getInt(FDIALOG_ANIMATIONSTYLE, 0);
            dialogTag = arguments.getString(FDIALOG_TAG, getClass().getSimpleName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(setContentView(), container, false);
            initView(mRootView);
            if (dialogViewListener != null) {
                dialogViewListener.getView(mRootView);
            }
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    public abstract void initView(View view);

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return creatDialog();
    }

    private Dialog creatDialog() {
        Dialog dialog = new Dialog(getActivity(), getTheme());
        dialog.setCanceledOnTouchOutside(canceledOutside);
        dialog.setCancelable(dialogCancelable);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setDimAmount(alpha);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = FConvertUtils.dip2px(offsetX);
            attributes.y = FConvertUtils.dip2px(offsetY);
            attributes.gravity = gravity;
            attributes.softInputMode = softinputmode;
            attributes.windowAnimations = animationStyle;
            window.setAttributes(attributes);
        }
        return dialog;

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (dialogDismissListener != null) {
            dialogDismissListener.onDismiss();
        }
    }

    public void setDialogViewListener(DialogViewListener dialogViewListener) {
        this.dialogViewListener = dialogViewListener;
    }

    public void setDialogDismissListener(DialogDismissListener dialogDismissListener) {
        this.dialogDismissListener = dialogDismissListener;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mRootView = null;
    }

    @IntDef({Gravity.TOP, Gravity.LEFT, Gravity.BOTTOM, Gravity.RIGHT, Gravity.CENTER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DialogGravity {
    }

    public interface DialogViewListener {
        void getView(View v);
    }

    public interface DialogDismissListener {
        void onDismiss();
    }
}
