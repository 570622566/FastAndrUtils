package com.hotapk.fastandrutils;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import cn.hotapk.fastandrutils.utils.FAssetsARawUtils;
import cn.hotapk.fastandrutils.utils.FImageUtils;

/**
 * @author laijian
 * @version 2017/11/9
 * @Copyright (C)下午2:50 , www.hotapk.cn
 * 图片相关
 */
public class FImageActivity extends FBaseActivity {

    private ImageView nulimg;
    private ImageView roundcornerimg;
    private ImageView cornerborderimg;
    private ImageView roundimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fimage);
        roundimg = (ImageView) findViewById(R.id.round_img);
        cornerborderimg = (ImageView) findViewById(R.id.cornerborder_img);
        roundcornerimg = (ImageView) findViewById(R.id.roundcorner_img);
        nulimg = (ImageView) findViewById(R.id.nul_img);

        Bitmap bitmap = FImageUtils.getBitmap(FAssetsARawUtils.getAssetsToInp("lmq.jpg"));
        nulimg.setImageBitmap(bitmap);
        roundcornerimg.setImageBitmap(FImageUtils.toRoundCorner(bitmap, 60));
        cornerborderimg.setImageBitmap(FImageUtils.toRoundCorner(bitmap, 60, 20, ContextCompat.getColor(this, R.color.title)));
        roundimg.setImageBitmap(FImageUtils.toRound(bitmap));

    }
}
