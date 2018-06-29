package cn.hotapk.fastandrutils.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author laijian
 * @version 2017/10/31
 * @Copyright (C)下午3:4
 * www.hotapk.cn
 */
public class FStatusBarUtils {
    private final static int STATUSBAR_TYPE_DEFAULT = 0;
    private final static int STATUSBAR_TYPE_MIUI = 1;
    private final static int STATUSBAR_TYPE_FLYME = 2;
    private final static int STATUSBAR_TYPE_ANDROID6 = 3; // Android 6.0
    private final static int STATUS_BAR_DEFAULT_HEIGHT_DP = 25; // 大部分状态栏都是25dp
    private static final int TAG_OFFSET = -123;
    private static final int TAG_OFFSETPADDING = -124;

    private static @StatusBarType
    int mStatuBarType = STATUSBAR_TYPE_DEFAULT;

    /**
     * 状态栏透明
     *
     * @param activity
     */
    public static void translucent(Activity activity) {
        allowtranslucent(activity, 0x40000000);
    }

    /**
     * 使用正常的设置系统状态栏颜色
     * xml 需要添加 android:fitsSystemWindows="true"
     *
     * @param activity
     * @param statusBarColor
     */
    public static void setSysStatusBar(Activity activity, @ColorInt int statusBarColor) {
        allowtranslucent(activity, statusBarColor);
    }

    /**
     * 使用正常的设置系统状态栏颜色
     * 1.xml 不需要添加 android:fitsSystemWindows="true"
     * 2.不能写在baseActivity
     *
     * @param activity
     * @param statusBarColor
     */
    public static void setSysStatusBarfit(Activity activity, @ColorInt int statusBarColor) {
        if (allowtranslucent(activity, statusBarColor)) {
            ViewGroup parent = activity.findViewById(android.R.id.content);
            View view = parent.getChildAt(0);
            if (!view.getFitsSystemWindows()) {
                view.setFitsSystemWindows(true);
            }
        }
    }

    /**
     * 让标题栏增加paddingTop的值
     * 注：这样就不用在 xml 添加 android:fitsSystemWindows="true"
     *
     * @param activity
     * @param view
     */
    public static void paddingTopStatusBar(Activity activity, @NonNull View view) {
        if (allowtranslucent(activity, 0x40000000)) {
            addPaddingTopEqualStatusBarHeight(view);
        }
    }

    /**
     * 让标题栏增加marginTop的值 伪装个状态栏宽高一样的view填充
     * 注：1、这样就不用在 xml 添加 android:fitsSystemWindows="true"
     * 2、不兼容DrawerLayout
     *
     * @param activity
     * @param view
     * @param statusBarColor
     */
    public static void marginTopStatusBar(Activity activity, View view, @ColorInt int statusBarColor) {
        if (allowtranslucent(activity, 0x40000000)) {
            setMargingTopStatusBarView(activity, view, statusBarColor, 1);
        }
    }

    /**
     * 让标题栏增加marginTop的值 伪装个状态栏宽高一样的view填充
     * 注：1、这样就不用在 xml 添加 android:fitsSystemWindows="true"
     * 2、不兼容DrawerLayout
     *
     * @param activity
     * @param view
     * @param statusBarColor
     * @param alpha          状态栏的透明度
     */
    public static void marginTopStatusBar(Activity activity, View view, @ColorInt int statusBarColor, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (allowtranslucent(activity, 0x40000000)) {
            setMargingTopStatusBarView(activity, view, statusBarColor, alpha);
        }
    }


    /**
     * 兼容drawerLayout的状态栏
     * 注：1、xml的顶部控件必须为drawerLayout
     * 2、drawerLayout第一个控件必须为装载内容的控件
     * 3、内容的控件必须为linearlayout
     *
     * @param activity
     * @param statusBarColor
     * @param alpha
     */
    public static void setStatusBarView4Draw(Activity activity, @ColorInt int statusBarColor, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (allowtranslucent(activity, 0x40000000)) {
            addStatusBar4Draw(activity, statusBarColor, alpha);
        }
    }

    public static void setStatusBarView4Draw(Activity activity, @ColorInt int statusBarColor) {
        if (allowtranslucent(activity, 0x40000000)) {
            addStatusBar4Draw(activity, statusBarColor, 1);
        }
    }

    /**
     * 设置状态栏的透明的随着标题栏的高度变化而变化
     * 注： 这方法不和DrawerLayout兼容
     *
     * @param activity
     * @param titleview
     * @param statusBarColor
     */
    public static void setAlphaStatusBar(final Activity activity, @NonNull final View titleview, final @ColorInt int statusBarColor) {
        if (!allowtranslucent(activity, 0x40000000)) {
            return;
        }

        titleview.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        // 判断view可见高度
                        Rect scrollBounds = new Rect();
                        titleview.getLocalVisibleRect(scrollBounds);
                        float alpha = (float) scrollBounds.top / FScreenUtils.getStatusHeight(activity);
                        if (alpha > 1 || alpha < 0) {
                            alpha = 1;
                        }
                        addStatusBarColor(activity, statusBarColor, alpha);
                    }
                });

    }


    /**
     * 把titleview添加margingtop的值，再伪装个view当状态栏
     *
     * @param activity
     * @param view
     * @param statusBarColor
     * @param alpha
     */
    private static void setMargingTopStatusBarView(Activity activity, View view, @ColorInt int statusBarColor, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        addMarginTopEqualStatusBarHeight(view);
        addStatusBarColor(activity, statusBarColor, alpha);
    }

    /**
     * 系统设置状态栏颜色
     *
     * @param activity
     * @param statusBarColor
     */
    @TargetApi(19)
    private static boolean allowtranslucent(Activity activity, @ColorInt int statusBarColor) {
        if (checkVersion()) {
            return false;
        }
        // 小米和魅族4.4 以上版本支持沉浸式
//        if (FDeviceUtils.isMeizu() || FDeviceUtils.isMIUI()) {
//            Window window = activity.getWindow();
//            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            return true;
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (statusBarColor == 0x40000000) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && supportTransclentStatusBar6()) {
                    // android 6以后可以改状态栏字体颜色，因此可以自行设置为透明
                    // ZUK Z1是个另类，自家应用可以实现字体颜色变色，但没开放接口
                    window.setStatusBarColor(Color.TRANSPARENT);
                } else {
                    window.setStatusBarColor(statusBarColor);
                }
            } else {
                window.setStatusBarColor(statusBarColor);
            }
        }
        return true;
    }


    /**
     * 为view增加MarginTop为状态栏高度
     *
     * @param view view
     */
    private static void addMarginTopEqualStatusBarHeight(@NonNull View view) {
        Object haveSetOffset = view.getTag(TAG_OFFSET);
        if (haveSetOffset != null && (Boolean) haveSetOffset) return;
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin,
                layoutParams.topMargin + FScreenUtils.getStatusHeight(view.getContext()),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
        view.setTag(TAG_OFFSET, true);
    }

    /**
     * 为view减少MarginTop为状态栏高度
     *
     * @param view view
     */
    public static void subtractMarginTopEqualStatusBarHeight(@NonNull View view) {
        Object haveSetOffset = view.getTag(TAG_OFFSET);
        if (haveSetOffset == null || !(Boolean) haveSetOffset) return;
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin,
                layoutParams.topMargin - FScreenUtils.getStatusHeight(view.getContext()),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
        view.setTag(TAG_OFFSET, false);
    }

    /**
     * 为view增加PaddingTop为状态栏高度
     *
     * @param view view
     */
    private static void addPaddingTopEqualStatusBarHeight(@NonNull final View view) {
        Object haveSetOffset = view.getTag(TAG_OFFSETPADDING);
        if (haveSetOffset != null && (Boolean) haveSetOffset) return;

        view.post(new Runnable() {
            @Override
            public void run() {

                view.setPadding(view.getPaddingLeft(),
                        view.getPaddingTop() + FScreenUtils.getStatusHeight(view.getContext()),
                        view.getPaddingRight(),
                        view.getPaddingBottom());
                ViewGroup.LayoutParams vparam = view.getLayoutParams();
                vparam.height = view.getBottom() + FScreenUtils.getStatusHeight(view.getContext());
                view.setLayoutParams(vparam);
                view.setTag(TAG_OFFSETPADDING, true);
                FLogUtils.getInstance().e(view.getHeight() + "");
            }
        });

    }

    /**
     * 为view减少PaddingTop为状态栏高度
     *
     * @param view view
     */
    public static void subtractPaddingTopEqualStatusBarHeight(@NonNull View view) {
        Object haveSetOffset = view.getTag(TAG_OFFSETPADDING);
        if (haveSetOffset == null || !(Boolean) haveSetOffset) return;
        view.setPadding(view.getPaddingLeft(),
                view.getPaddingTop() - FScreenUtils.getStatusHeight(view.getContext()),
                view.getPaddingRight(),
                view.getPaddingBottom());
        view.setTag(TAG_OFFSETPADDING, true);
    }

    /**
     * 添加伪装的状态栏的view
     *
     * @param activity
     * @param color
     * @param alpha
     */
    private static void addStatusBarColor(final Activity activity, final int color, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        ViewGroup parent = activity.findViewById(android.R.id.content);
        View fakeStatusBarView = parent.findViewWithTag(activity.getClass().getSimpleName());
        if (fakeStatusBarView != null) {
            if (fakeStatusBarView.getVisibility() == View.GONE) {
                fakeStatusBarView.setVisibility(View.VISIBLE);
            }
            fakeStatusBarView.setBackgroundColor(color);
            fakeStatusBarView.setAlpha(alpha);
        } else {
            parent.addView(createColorStatusBarView(parent.getContext(), color));
        }
    }

    /**
     * 添加一个兼容drawerLayout的状态栏
     *
     * @param activity
     * @param color
     * @param alpha
     */
    private static void addStatusBar4Draw(final Activity activity, final int color, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        ViewGroup parent = activity.findViewById(android.R.id.content);
        View fakeStatusBarView = parent.findViewWithTag(activity.getClass().getSimpleName());
        if (fakeStatusBarView != null) {
            if (fakeStatusBarView.getVisibility() == View.GONE) {
                fakeStatusBarView.setVisibility(View.VISIBLE);
            }
            fakeStatusBarView.setBackgroundColor(color);
            fakeStatusBarView.setAlpha(alpha);
        } else {
            //获取drawerLayout
            ViewGroup drawerLayout = (ViewGroup) parent.getChildAt(0);
            //获取drawerLayout第一个控件，这控件必须为linearlayout
            ViewGroup linearlayout = (ViewGroup) drawerLayout.getChildAt(0);
            //在linearlayout中插入自定义的状态栏view到顶部
            linearlayout.addView(createColorStatusBarView(parent.getContext(), color), 0);
        }
    }


    /**
     * 绘制一个和状态栏一样高的颜色矩形
     */
    private static View createColorStatusBarView(final Context context, final int color) {
        View statusBarView = new View(context);
        statusBarView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, FScreenUtils.getStatusHeight(context)));
        statusBarView.setBackgroundColor(color);
        statusBarView.setTag(context.getClass().getSimpleName());
        return statusBarView;
    }

    /**
     * 添加一个状态栏大小的view
     *
     * @param rootview
     * @param color
     * @param viewposition
     */
    public static void addBarView(ViewGroup rootview, final int color, int viewposition) {
        rootview.addView(createColorStatusBarView(rootview.getContext(), color), viewposition);
    }

    /**
     * 设置状态栏黑色字体图标，
     * 支持 4.4 以上版本 MIUI 和 Flyme，以及 6.0 以上版本的其他 Android
     *
     * @param activity 需要被处理的 Activity
     */
    public static boolean setStatusBarDarktMode(Activity activity) {
        // 无语系列：ZTK C2016只能时间和电池图标变色。。。。
        if (FDeviceUtils.isZTKC2016()) {
            return false;
        }

        if (mStatuBarType != STATUSBAR_TYPE_DEFAULT) {
            return setStatusBarDarkMode(activity, mStatuBarType);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isMIUICustomStatusBarLightModeImpl() && MIUISetStatusBarDarkMode(activity.getWindow(), true)) {
                mStatuBarType = STATUSBAR_TYPE_MIUI;
                return true;
            } else if (FlymeSetStatusBarDarkMode(activity.getWindow(), true)) {
                mStatuBarType = STATUSBAR_TYPE_FLYME;
                return true;
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Android6SetStatusBarLightMode(activity.getWindow(), true);
                mStatuBarType = STATUSBAR_TYPE_ANDROID6;
                return true;
            }
        }
        return false;
    }

    /**
     * 已知系统类型时，设置状态栏黑色字体图标。
     * 支持 4.4 以上版本 MIUI 和 Flyme，以及 6.0 以上版本的其他 Android
     *
     * @param activity 需要被处理的 Activity
     * @param type     StatusBar 类型，对应不同的系统
     */
    private static boolean setStatusBarDarkMode(Activity activity, @StatusBarType int type) {
        if (type == STATUSBAR_TYPE_MIUI) {
            return MIUISetStatusBarDarkMode(activity.getWindow(), true);
        } else if (type == STATUSBAR_TYPE_FLYME) {
            return FlymeSetStatusBarDarkMode(activity.getWindow(), true);
        } else if (type == STATUSBAR_TYPE_ANDROID6) {
            return Android6SetStatusBarLightMode(activity.getWindow(), true);
        }
        return false;
    }


    /**
     * 设置状态栏白色字体图标
     * 支持 4.4 以上版本 MIUI 和 Flyme，以及 6.0 以上版本的其他 Android
     */
    public static boolean setStatusBarLightMode(Activity activity) {
        if (mStatuBarType == STATUSBAR_TYPE_DEFAULT) {
            // 默认状态，不需要处理
            return true;
        }

        if (mStatuBarType == STATUSBAR_TYPE_MIUI) {
            return MIUISetStatusBarDarkMode(activity.getWindow(), false);
        } else if (mStatuBarType == STATUSBAR_TYPE_FLYME) {
            return FlymeSetStatusBarDarkMode(activity.getWindow(), false);
        } else if (mStatuBarType == STATUSBAR_TYPE_ANDROID6) {
            return Android6SetStatusBarLightMode(activity.getWindow(), false);
        }
        return true;
    }

    @TargetApi(23)
    private static int changeStatusBarModeRetainFlag(Window window, int out) {
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_FULLSCREEN);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        out = retainSystemUiFlag(window, out, View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        return out;
    }

    private static int retainSystemUiFlag(Window window, int out, int type) {
        int now = window.getDecorView().getSystemUiVisibility();
        if ((now & type) == type) {
            out |= type;
        }
        return out;
    }


    /**
     * 设置状态栏字体图标为深色，Android 6
     *
     * @param window 需要设置的窗口
     * @param light  是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    @TargetApi(23)
    private static boolean Android6SetStatusBarLightMode(Window window, boolean light) {
        View decorView = window.getDecorView();
        int systemUi = light ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        systemUi = changeStatusBarModeRetainFlag(window, systemUi);
        decorView.setSystemUiVisibility(systemUi);
        return true;
    }

    /**
     * 设置状态栏字体图标为深色，需要 MIUIV6 以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回 true
     */
    @SuppressWarnings("unchecked")
    private static boolean MIUISetStatusBarDarkMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception ignored) {

            }
        }
        return result;
    }

    /**
     * 更改状态栏图标、文字颜色的方案是否是MIUI自家的， MIUI9之后用回Android原生实现
     * 见小米开发文档说明：https://dev.mi.com/console/doc/detail?pId=1159
     */
    private static boolean isMIUICustomStatusBarLightModeImpl() {
        return FDeviceUtils.isMIUIV5() || FDeviceUtils.isMIUIV6() ||
                FDeviceUtils.isMIUIV7() || FDeviceUtils.isMIUIV8();
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为 Flyme 用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean FlymeSetStatusBarDarkMode(Window window, boolean dark) {

        // flyme 在 6.2.0.0A 支持了 Android 官方的实现方案，旧的方案失效
        Android6SetStatusBarLightMode(window, dark);

        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception ignored) {

            }
        }
        return result;
    }


    /**
     * 检测 Android 6.0 是否可以启用 window.setStatusBarColor(Color.TRANSPARENT)。
     */
    private static boolean supportTransclentStatusBar6() {
        return !(FDeviceUtils.isZUKZ1() || FDeviceUtils.isZTKC2016());
    }


    /**
     * 检测版本
     *
     * @return
     */
    private static boolean checkVersion() {
        return Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT;
    }

    @IntDef({STATUSBAR_TYPE_DEFAULT, STATUSBAR_TYPE_MIUI, STATUSBAR_TYPE_FLYME, STATUSBAR_TYPE_ANDROID6})
    @Retention(RetentionPolicy.SOURCE)
    private @interface StatusBarType {
    }
}
