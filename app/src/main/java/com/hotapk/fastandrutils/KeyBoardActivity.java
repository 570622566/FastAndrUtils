package com.hotapk.fastandrutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hotapk.fastandrlibs.utils.FKeyBoardUI;

public class KeyBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
        FKeyBoardUI.buildKeyBoardUI(this);
    }
}
