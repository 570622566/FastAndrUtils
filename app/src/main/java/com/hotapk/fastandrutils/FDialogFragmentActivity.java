package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hotapk.fastandrutils.fragment.MyDialogFragment;

import cn.hotapk.fastandrutils.utils.FLogUtils;

public class FDialogFragmentActivity extends FBaseActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdialog_fragment);
        button = findViewById(R.id.show_bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                FLogUtils.getInstance().e("button.getBottom()==" + button.getBottom());
                MyDialogFragment myDialogFragment = MyDialogFragment.newInstance(button.getBottom());
                myDialogFragment.show(fragmentTransaction, "dialog");
            }
        });
    }
}
