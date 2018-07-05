package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import cn.hotapk.fastandrutils.utils.FLogUtils;
import cn.hotapk.fastandrutils.widget.FEmptyView;

public class FEmptyActivity extends FBaseActivity {
    private FEmptyView empty_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fempty);
        empty_layout = (FEmptyView) findViewById(R.id.empty_layout);

        findViewById(R.id.hasdata_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty_layout.dismissEmptyView();
            }
        });

        findViewById(R.id.lodding_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty_layout.loadding();
            }
        });

        findViewById(R.id.nodata_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty_layout.noData();
            }
        });
        findViewById(R.id.networkerror_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty_layout.networkError();
            }
        });
        findViewById(R.id.other_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty_layout.showOtherView(R.layout.other_empty_view);
            }
        });
        empty_layout.setChildViewOnClickListener(new FEmptyView.OnChildViewOnClickListener() {
            @Override
            public void childViewOnclick(int viewId) {
                if (viewId == R.layout.other_empty_view) {
                    FLogUtils.getInstance().e("eeeee---");

                }
            }
        });
        empty_layout.setEmptyViewListener(new FEmptyView.EmptyViewListener() {
            @Override
            public void getEmptyView(ViewGroup view, int viewid) {

            }
        });

    }
}
