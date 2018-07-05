package cn.hotapk.fastandrutils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import cn.hotapk.fastandrutils.utils.FResourcesUtils;


/**
 * @author laijian
 * @version 2018/6/1
 * @Copyright (C)上午10:20 , www.hotapk.cn
 * 空提示界面
 */

public class FEmptyView extends FrameLayout {
    private LinearLayout emptyLayout;//空界面容器
    private View emptyView;//空布局布局
    private View dataView;
    private Context context;
    private int loddingId;
    private int nodataId;
    private int networkerrorId;
    private int otherId;
    private OnChildViewOnClickListener childViewOnClickListener;
    private EmptyViewListener emptyViewListener;

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        if (dataView == null) {
            dataView = child;
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * 初始化布局
     */
    private void initView(TypedArray array) {
        loddingId = array.getResourceId(FResourcesUtils.getStyleable("EmptyLayout_loadding_layout"), FResourcesUtils.getLayoutResources("f_loadding_view"));
        networkerrorId = array.getResourceId(FResourcesUtils.getStyleable("EmptyLayout_networkerror_layout"), FResourcesUtils.getLayoutResources("f_networkerror_view"));
        nodataId = array.getResourceId(FResourcesUtils.getStyleable("EmptyLayout_nodata_layout"), FResourcesUtils.getLayoutResources("f_nodata_view"));
        initEmptyLayout();
        addView(emptyLayout);
    }

    private void initEmptyLayout() {
        emptyLayout = new LinearLayout(context);
        FrameLayout.LayoutParams layoutParams = new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        emptyLayout.setOrientation(LinearLayout.VERTICAL);
        emptyLayout.setLayoutParams(layoutParams);
    }

    /**
     * 加载
     */
    public void loadding() {
        showEmptyView(loddingId);
    }

    public void loadding(@LayoutRes int loddingId) {
        this.loddingId = loddingId;
        showEmptyView(loddingId);
    }

    /**
     * 没数据
     */
    public void noData() {
        showEmptyView(nodataId);
    }

    public void noData(@LayoutRes int nodataId) {
        this.nodataId = nodataId;
        showEmptyView(nodataId);
    }

    /**
     * 网络出错
     */
    public void networkError() {
        showEmptyView(networkerrorId);
    }

    public void networkError(@LayoutRes int networkerrorId) {
        this.networkerrorId = networkerrorId;
        showEmptyView(networkerrorId);
    }

    /**
     * 显示自定义view
     *
     * @param otherId
     */
    public void showOtherView(@LayoutRes int otherId) {
        this.otherId = otherId;
        showEmptyView(otherId);
    }


    /**
     * 获取加载窗的layout id
     *
     * @return
     */
    public int getLoddingId() {
        return loddingId;
    }

    /**
     * 获取没数据layout id
     *
     * @return
     */
    public int getNodataId() {
        return nodataId;
    }

    /**
     * 获取网络异常layout id
     *
     * @return
     */
    public int getNetworkerrorId() {
        return networkerrorId;
    }

    /**
     * 获取自定义的layout id
     *
     * @return
     */
    public int getOtherId() {
        return otherId;
    }


    public void setChildViewOnClickListener(OnChildViewOnClickListener childViewOnClickListener) {
        this.childViewOnClickListener = childViewOnClickListener;

    }

    public void setEmptyViewListener(EmptyViewListener emptyViewListener) {
        this.emptyViewListener = emptyViewListener;
    }

    private void showEmptyView(int viewid) {
        if (emptyLayout.getChildCount() != 0) {
            emptyLayout.removeAllViews();
        }
        addChildViewid(viewid);
        emptyLayout.setVisibility(VISIBLE);
        dataView.setVisibility(GONE);

    }

    /**
     * 隐藏空界面
     */
    public void dismissEmptyView() {
        emptyLayout.removeAllViews();
        dataView.setVisibility(VISIBLE);
        emptyLayout.setVisibility(GONE);
        emptyView = null;
    }

    /**
     * 添加数据View
     *
     * @param viewid
     */
    private void addChildViewid(int viewid) {
        View childView = LayoutInflater.from(context).inflate(viewid, this, false);
        addChildView(childView, viewid);
    }

    private void addChildView(View childView, int viewid) {
        emptyView = childView;
        emptyView.setId(viewid);
        emptyLayout.addView(childView);
        emptyView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childViewOnClickListener != null)
                    childViewOnClickListener.childViewOnclick(emptyView.getId());
            }
        });
        if (emptyViewListener != null) {
            emptyViewListener.getEmptyView((ViewGroup) emptyView, emptyView.getId());
        }
    }

    public interface OnChildViewOnClickListener {
        void childViewOnclick(int viewId);
    }

    public interface EmptyViewListener {
        void getEmptyView(ViewGroup view, int viewid);
    }


}
