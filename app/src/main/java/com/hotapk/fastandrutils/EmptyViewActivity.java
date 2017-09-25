package com.hotapk.fastandrutils;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import cn.hotapk.fastandrutils.widget.FEmptyView;

public class EmptyViewActivity extends AppCompatActivity {

    private FEmptyView empty_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_view);
        empty_lay = (FEmptyView) findViewById(R.id.empty_lay);
        empty_lay.loadding("正在加载数据。。。");
//        empty_lay.loadding("正在加载数据。。。", new FEmptyView.ImgCallBack() {
//            @Override
//            public void setImg(ImageView img, int emptyType) {
//                img.setBackgroundResource(R.mipmap.loadding);
//                // 加载动画
//                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
//                        EmptyViewActivity.this, R.anim.progress_round);
//                // 使用ImageView显示动画
//                img.startAnimation(hyperspaceJumpAnimation);
//            }
//        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                empty_lay.loaddingFail("加载数据失败。。。", "点击刷新", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadding();
                    }
                }, new FEmptyView.ImgCallBack() {
                    @Override
                    public void setImg(ImageView img, int emptyType) {
                        img.setBackgroundResource(R.mipmap.loaddingfail);
                        if (img.getAnimation() != null)
                            img.getAnimation().cancel();
                    }
                });

            }
        }, 1500);

    }

    private void loadding() {
        empty_lay.loadding("正在加载数据。。。", new FEmptyView.ImgCallBack() {
            @Override
            public void setImg(ImageView img, int emptyType) {
                img.setBackgroundResource(R.mipmap.loadding);
                // 加载动画
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                        EmptyViewActivity.this, R.anim.progress_round);
                // 使用ImageView显示动画
                img.startAnimation(hyperspaceJumpAnimation);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                empty_lay.loaddingSuccess();
            }
        }, 1500);
    }


}
