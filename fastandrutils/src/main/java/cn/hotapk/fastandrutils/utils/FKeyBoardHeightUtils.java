package cn.hotapk.fastandrutils.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * @author laijian
 * @version 2017/8/31
 * @Copyright (C)上午11:11 , Suntektech
 * 获取键盘高度
 */
public class FKeyBoardHeightUtils {
    private View mChildOfContent;//activity 的布局View
    private int usableHeightPrevious;//activity的View的可视高度
    private KeyBoardVisiableListener keyBoardHigthListener;
    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {

        @Override
        public void onGlobalLayout() {
            possiblyResizeChildOfContent();
        }
    };

    private FKeyBoardHeightUtils(final Activity activity, KeyBoardVisiableListener keyBoardHigthListener) {
        this.keyBoardHigthListener = keyBoardHigthListener;
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);//监听视图高度变化
        mChildOfContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FKeyBoardUtils.hideInputForce(activity);
            }
        });
    }

    public static FKeyBoardHeightUtils setKeyBoardHeigthListener(Activity activity, KeyBoardVisiableListener keyBoardHigthListener) {
        return new FKeyBoardHeightUtils(activity, keyBoardHigthListener);
    }

    @SuppressLint("ObsoleteSdkInt")
    public void removeKeyboardHeightListener() {
        if (mChildOfContent == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            mChildOfContent.getViewTreeObserver().removeGlobalOnLayoutListener(globalLayoutListener);
        } else {
            mChildOfContent.getViewTreeObserver().removeOnGlobalLayoutListener(globalLayoutListener);
        }
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                keyBoardHigthListener.showKeyBoard(usableHeightSansKeyboard - heightDifference, mChildOfContent);
            } else {
                keyBoardHigthListener.hideKeyBoard(usableHeightSansKeyboard, mChildOfContent);
            }
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }


    interface KeyBoardVisiableListener {
        void showKeyBoard(int heigth, View contentView);

        void hideKeyBoard(int heigth, View contentView);
    }


}
