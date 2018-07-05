package com.hotapk.fastandrutils.statusbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hotapk.fastandrutils.R;

import cn.hotapk.fastandrutils.utils.FStatusBarUtils;

public class PaddingTopBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padding_top_bar);
        FStatusBarUtils.paddingTopStatusBar(this, findViewById(R.id.title_name));
    }
}
