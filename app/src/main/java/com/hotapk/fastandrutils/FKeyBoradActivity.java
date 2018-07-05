package com.hotapk.fastandrutils;

import android.os.Bundle;

import cn.hotapk.fastandrutils.utils.FKeyBoardUI;

public class FKeyBoradActivity extends FBaseActivity {
    private FKeyBoardUI fKeyBoardUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fkey_borad);
        fKeyBoardUI = FKeyBoardUI.buildKeyBoardUI(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fKeyBoardUI.removeKeyboardHeightListener();
    }
}
