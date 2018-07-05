package cn.hotapk.fastandrutils.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.hotapk.fastandrutils.widget.FNullMenuEditText;


/**
 * @author laijian
 * @version 2017/8/31
 * @Copyright (C)上午11:57 , Suntektech
 * 软件键盘结合EditText
 */
public class FKeyBoardUI implements FKeyBoardHeightUtils.KeyBoardVisiableListener {

    private EditText edtextVew;//activity的输入框
    private Activity activity;
    private Dialog mDialog;
    private FNullMenuEditText popuEdtext;//popu的输入框
    private int screenWeight = 0;//屏幕宽度
    private FKeyBoardHeightUtils keyBoardHeightUtils;

    private FKeyBoardUI(Activity activity) {
        this.activity = activity;
        getScreen();
        initDialog();
        keyBoardHeightUtils = FKeyBoardHeightUtils.setKeyBoardHeigthListener(activity, this);
    }

    public static FKeyBoardUI buildKeyBoardUI(Activity activity) {
        return new FKeyBoardUI(activity);
    }

    private void getScreen() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWeight = dm.widthPixels;
    }

    private void initDialog() {
        LayoutInflater inflater = (LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popuView = inflater.inflate(FResourcesUtils.getLayoutResources("f_popuwindow"), null);
        RelativeLayout populay = (RelativeLayout) popuView.findViewById(FResourcesUtils.getIdResources("popu_lay"));
        popuEdtext = (FNullMenuEditText) popuView.findViewById(FResourcesUtils.getIdResources("ed_text"));
        mDialog = new Dialog(activity, FResourcesUtils.getStyleResources("f_dialog"));
        mDialog.setContentView(popuView);
        populay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FKeyBoardUtils.closeKeybord(popuEdtext, activity);
                mDialog.dismiss();
            }
        });

    }


    private boolean checkViewVisiable() {
        Rect localRect = new Rect();
        boolean visiable = edtextVew.getLocalVisibleRect(localRect);
        return visiable;
    }

    private void onEdChange() {
        String hintStr = "";
        String text = "";
        if (!TextUtils.isEmpty(edtextVew.getText())) {
            text = edtextVew.getText().toString();
        }
        if (!TextUtils.isEmpty(edtextVew.getHint())) {
            hintStr = edtextVew.getHint().toString();
        }
        popuEdtext.findFocus();
        popuEdtext.setInputType(edtextVew.getInputType());
        popuEdtext.setHint(hintStr);
        popuEdtext.setText(text);
        popuEdtext.setSelection(text.length());
        popuEdtext.setMaxEms(edtextVew.getMaxEms());
        popuEdtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtextVew.setText(s);
                edtextVew.setSelection(s.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        popuEdtext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == 0) {
                    FKeyBoardUtils.closeKeybord(popuEdtext, activity);
                    mDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    public void removeKeyboardHeightListener() {
        keyBoardHeightUtils.removeKeyboardHeightListener();
    }

    @Override
    public void showKeyBoard(int heigth, View contentView) {
        if (contentView != null) {
            View childView = contentView.findFocus();
            if (childView != null) {
                if (childView instanceof EditText) {
                    edtextVew = (EditText) childView;
                    if (!checkViewVisiable()) {
                        mDialog.show();
                        Window dialogWindow = mDialog.getWindow();
                        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                        p.height = heigth; // 高度设置为屏幕的0.6，根据实际情况调整
                        p.width = screenWeight; // 宽度设置为屏幕的0.65，根据实际情况调整
                        dialogWindow.setAttributes(p);
                        dialogWindow.setWindowAnimations(FResourcesUtils.getStyleResources("f_popupAnimation"));
                        onEdChange();
                        FKeyBoardUtils.openKeybord(edtextVew, activity);
                    } else {
                        if (mDialog != null) {
                            mDialog.dismiss();
                        }
                    }
                }
            }
        }


    }

    @Override
    public void hideKeyBoard(int heigth, View contentView) {
        mDialog.dismiss();
    }
}
