package com.hotapk.fastandrutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.hotapk.fastandrlibs.utils.FFileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtilsActivity extends AppCompatActivity {
    TextView bytime_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_utils);
        bytime_bt = (TextView) findViewById(R.id.bytime_bt);
        bytime_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File[] files = FFileUtils.orderByDate(new File(FFileUtils.getRootDir()), true);
                for (File f : files) {
                    System.out.println(f.getName() + "==" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(f.lastModified())));
                }
            }
        });
    }
}
