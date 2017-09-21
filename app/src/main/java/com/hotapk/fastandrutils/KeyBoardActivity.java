package com.hotapk.fastandrutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.hotapk.fastandrutils.utils.FKeyBoardUI;

public class KeyBoardActivity extends AppCompatActivity {

    Button bt_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
        bt_bt = (Button) findViewById(R.id.bt_bt);
        FKeyBoardUI.buildKeyBoardUI(this);
        bt_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = null;
                int a = Integer.parseInt(s);
                System.out.println(a);
            }
        });
    }
}
