package cn.hotapk.fastandrutils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.hotapk.fastandrutils.utils.FResourcesUtils;

/**
 * @author laijian
 * @version 2017/9/23
 * @Copyright (C)下午1:03 , www.hotapk.cn
 * 空提示界面
 */

public class FEmptyView extends FrameLayout {
    private LinearLayout setdataLay;//设置数据布局
    private View emptyView;//空布局
    private ImageView emptyImg;//空布局的ImageView
    private TextView emptyTv;//空布局的TextView
    private Button emptyBt;//空布局的Button
    private Context context;


    public FEmptyView(@NonNull Context context) {
        this(context, null);
    }

    public FEmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FEmptyView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, FResourcesUtils.getStyleableArray("EmptyLayout"), 0, 0);
        initView(array);
        array.recycle();
    }

    /**
     * 初始化布局
     */
    private void initView(TypedArray array) {
        int emptyViewId = array.getResourceId(FResourcesUtils.getStyleable("EmptyLayout_empty_layout"), FResourcesUtils.getLayoutResources("f_empty_layout"));
        int emptyImgId = array.getResourceId(FResourcesUtils.getStyleable("EmptyLayout_empty_imageView"), FResourcesUtils.getIdResources("empty_img"));
        int emptyTvId = array.getResourceId(FResourcesUtils.getStyleable("EmptyLayout_empty_textView"), FResourcesUtils.getIdResources("empty_tv"));
        int emptyBtId = array.getResourceId(FResourcesUtils.getStyleable("EmptyLayout_empty_button"), FResourcesUtils.getIdResources("empty_bt"));
        int dataViewId = array.getResourceId(FResourcesUtils.getStyleable("EmptyLayout_include_layout"), 0);
        //获取空布局View
        emptyView = View.inflate(context, emptyViewId, null);
        //获取空布局的imageView
        emptyImg = (ImageView) emptyView.findViewById(emptyImgId);
        emptyTv = (TextView) emptyView.findViewById(emptyTvId);
        emptyBt = (Button) emptyView.findViewById(emptyBtId);
        setdataLay = new LinearLayout(context);
        setdataLay.setOrientation(LinearLayout.VERTICAL);
        setdataLay.setVisibility(GONE);
        if (dataViewId != 0) {
            addChildViewid(dataViewId);
        }
        addView(setdataLay);
        addView(emptyView);
    }


    public View getEmptyView() {
        return emptyView;
    }

    /**
     * 添加数据View
     *
     * @param viewid
     */
    public void addChildViewid(int viewid) {
        View childView = LayoutInflater.from(context).inflate(viewid, null, true);
        addChildView(childView);
    }

    public void addChildView(View childView) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        childView.setLayoutParams(layoutParams);
        setdataLay.addView(childView);
    }

    /**
     * 加载数据
     */
    public void loadding() {
        setLoadingShowing(true);
    }

    /**
     * 返回自定义emptyView
     *
     * @param viewCallBack
     */
    public void loadding(ViewCallBack viewCallBack) {
        setLoadingShowing(true);
        if (viewCallBack != null) {
            viewCallBack.emptyViewCallBack(emptyView);
        }
    }

    /**
     * 带返回imageView的加载框
     *
     * @param imgCallBack
     */
    public void loadding(ImgCallBack imgCallBack) {
        setLoadingShowing(true);
        setTitle(null);
        setBtText(null);
        setImgCallBack(imgCallBack, 1);
    }


    /**
     * 只显示title的加框
     *
     * @param title
     */
    public void loadding(String title) {
        setLoadingShowing(true);
        setTitle(title);
        setBtText(null);
    }

    /**
     * 带title，返回imageView的加载框
     */
    public void loadding(String title, ImgCallBack imgCallBack) {
        setLoadingShowing(true);
        setTitle(title);
        setBtText(null);
        setImgCallBack(imgCallBack, 1);
    }


    /**
     * 加载数据成功
     */
    public void loaddingSuccess() {
        setLoadingShowing(false);
    }

    public void loaddingSuccess(ImgCallBack imgCallBack) {
        setLoadingShowing(false);
        setImgCallBack(imgCallBack, 0);
    }

    public void loaddingSuccess(ViewCallBack viewCallBack) {
        setLoadingShowing(false);
        if (viewCallBack != null) {
            viewCallBack.emptyViewCallBack(emptyView);
        }
    }

    /**
     * 加载数据失败
     */
    public void loaddingFail() {
        setLoadingShowing(true);

    }

    public void loaddingFail(ViewCallBack viewCallBack) {
        setLoadingShowing(true);
        if (viewCallBack != null) {
            viewCallBack.emptyViewCallBack(emptyView);
        }
    }

    public void loaddingFail(String title, ImgCallBack imgCallBack) {
        setLoadingShowing(true);
        setTitle(title);
        setBtText(null);
        setImgCallBack(imgCallBack, 2);
    }


    public void loaddingFail(String title, String btTitle, OnClickListener onClickListener, ImgCallBack imgCallBack) {
        setLoadingShowing(true);
        setTitle(title);
        setOnClickRefreshListener(btTitle, onClickListener);
        setImgCallBack(imgCallBack, 2);
    }

    /**
     * 是否显示或隐藏空界面
     *
     * @param showing
     */
    public void setLoadingShowing(boolean showing) {
        setdataLay.setVisibility(showing ? GONE : VISIBLE);
        emptyView.setVisibility(showing ? VISIBLE : GONE);
    }

    private void setImgCallBack(ImgCallBack imgCallBack, int emptyType) {
        if (imgCallBack != null) {
            if (emptyImg != null) {
                imgCallBack.setImg(emptyImg, emptyType);
            }
        }
    }

    /**
     * 是否在空界面
     *
     * @return
     */
    public boolean isEmptyViewVisiable() {
        return emptyView.getVisibility() == VISIBLE;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (emptyTv != null) {
            emptyTv.setText(title);
            emptyTv.setVisibility(TextUtils.isEmpty(title) ? GONE : VISIBLE);
        }
    }


    /**
     * 设置标题字体颜色
     *
     * @param color
     */
    public void setTitleColor(int color) {
        if (emptyTv != null) {
            emptyTv.setTextColor(color);
        }
    }

    /**
     * 设置字体大小
     *
     * @param size
     */
    public void setTitleSize(float size) {
        if (emptyTv != null) {
            emptyTv.setTextSize(size);
        }
    }

    /**
     * 设置button的标题
     *
     * @param btTitle
     */
    public void setBtText(String btTitle) {
        if (emptyBt != null) {
            emptyBt.setText(btTitle);
            emptyBt.setVisibility(TextUtils.isEmpty(btTitle) ? GONE : VISIBLE);
        }
    }

    /**
     * 设置button字体颜色
     *
     * @param btColor
     */
    public void setBtTitleColor(int btColor) {
        if (emptyBt != null) {
            emptyBt.setTextColor(btColor);
        }
    }

    /**
     * 设置button字体大小
     *
     * @param btSize
     */
    public void setBtTitleSize(float btSize) {
        if (emptyBt != null) {
            emptyBt.setTextSize(btSize);
        }
    }

    /**
     * 设置button的背景颜色
     *
     * @param btResid
     */
    public void setBtBackground(int btResid) {
        if (emptyBt != null) {
            emptyBt.setBackgroundResource(btResid);
        }
    }

    /**
     * 刷新数据
     *
     * @param onClickListener
     */
    public void setOnClickRefreshListener(String btTitle, OnClickListener onClickListener) {
        if (emptyBt != null) {
            setBtText(btTitle);
            emptyBt.setOnClickListener(onClickListener);
        }
    }


    /**
     * ImagView 回调
     */
    public interface ImgCallBack {
        void setImg(ImageView img, int emptyType);
    }

    /**
     * emptyView 回调
     */
    public interface ViewCallBack {
        void emptyViewCallBack(View view);
    }
}
