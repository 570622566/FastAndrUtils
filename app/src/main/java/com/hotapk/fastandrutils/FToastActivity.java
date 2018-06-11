package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.hotapk.fastandrutils.utils.FToastUtils;

/**
 * @author laijian
 * @version 2017/11/6
 * @Copyright (C)下午3:07 , www.hotapk.cn
 * Toast显示
 */
public class FToastActivity extends FBaseActivity implements View.OnClickListener {

    private TextView nol_tv;
    private TextView center_tv;
    private TextView colorbg_tv;
    private TextView img_tv;
    private TextView round_tv;
    private TextView vimg_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftoast);
        nol_tv = (TextView) findViewById(R.id.nol_tv);
        center_tv = (TextView) findViewById(R.id.center_tv);
        colorbg_tv = (TextView) findViewById(R.id.colorbg_tv);
        img_tv = (TextView) findViewById(R.id.img_tv);
        round_tv = (TextView) findViewById(R.id.round_tv);
        vimg_tv = (TextView) findViewById(R.id.vimg_tv);

        nol_tv.setOnClickListener(this);
        center_tv.setOnClickListener(this);
        colorbg_tv.setOnClickListener(this);
        img_tv.setOnClickListener(this);
        round_tv.setOnClickListener(this);
        vimg_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nol_tv:
                FToastUtils.init().setDuration(100l).showLong("正常显示");
                break;
            case R.id.center_tv:
                FToastUtils.init().setGrivity(Gravity.CENTER).show("居中显示");
                break;
            case R.id.colorbg_tv:
                FToastUtils.init().setBgcolor(R.color.title).show("背景颜色");
                break;
            case R.id.img_tv:
                FToastUtils.init().setImgResid(R.mipmap.f_launcher).show("图片文字横向显示");
                break;
            case R.id.vimg_tv:
                FToastUtils.init().setImgResid(R.mipmap.f_launcher).setGrivity(Gravity.CENTER).setDirection(LinearLayout.VERTICAL).setDuration(800l).show("图片文字纵向显示");
                break;
            case R.id.round_tv:
                FToastUtils.init().setRoundRadius(30).show("自定义圆角");
                break;

        }
    }
}
