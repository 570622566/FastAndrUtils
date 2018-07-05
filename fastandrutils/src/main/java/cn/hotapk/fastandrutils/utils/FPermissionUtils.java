package cn.hotapk.fastandrutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author laijian
 * @version 2017/9/20
 * @Copyright (C)下午2:49 , www.hotapk.cn
 * Permission
 */
public class FPermissionUtils {
    private static int mRequestCode = -1;

    private OnPermissionListener mOnPermissionListener;

    private Activity activity;

    public FPermissionUtils(Activity activity) {
        this.activity = activity;
    }

    /**
     * 请求权限
     *
     * @param requestCode
     * @param permissions
     * @param listener
     */
    public void requestPermissions(int requestCode
            , String[] permissions, OnPermissionListener listener) {
        requestPermissions(requestCode, permissions, listener, null);
    }

    /**
     * 请求权限
     *
     * @param requestCode
     * @param permissions
     * @param listener
     * @param handler
     */
    public void requestPermissions(int requestCode
            , String[] permissions, OnPermissionListener listener, RationaleHandler handler) {

        mRequestCode = requestCode;
        mOnPermissionListener = listener;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (mOnPermissionListener != null) {
                mOnPermissionListener.onPermissionGranted();
            }
        } else {
            String[] deniedPermissions = getDeniedPermissions(permissions);
            if (deniedPermissions.length > 0) {
                boolean rationale = shouldShowRequestPermissionRationale(deniedPermissions);
                if (rationale && handler != null) {
                    handler.showRationale(activity, requestCode, deniedPermissions);
                } else {
                    activity.requestPermissions(deniedPermissions, requestCode);
                }
            } else {
                if (mOnPermissionListener != null)
                    mOnPermissionListener.onPermissionGranted();
            }
        }
    }

    /**
     * 请求权限结果，对应Activity中onRequestPermissionsResult()方法。
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        if (mRequestCode != -1 && requestCode == mRequestCode) {
            if (mOnPermissionListener != null) {
                String[] manifestPer = checkManifestPermission(permissions);
                String[] deniedPermissions = getDeniedPermissions(manifestPer);
                if (deniedPermissions.length > 0) {
                    mOnPermissionListener.onPermissionDenied(deniedPermissions);
                } else {
                    mOnPermissionListener.onPermissionGranted();
                }
                mOnPermissionListener.manifestUnPermission(manifestUnPermission(manifestPer, permissions));
            }
        }
    }

    /**
     * manifest 没注册该权限
     *
     * @param manifestPer
     * @param permissions
     * @return
     */
    private String[] manifestUnPermission(String[] manifestPer, String[] permissions) {

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < permissions.length; i++) {
            map.put(permissions[i], i);
        }

        for (int i = 0; i < manifestPer.length; i++) {
            if (map.containsKey(manifestPer[i])) {
                map.remove(manifestPer[i]);
            }
        }
        Set<String> key = map.keySet();
        List<String> unperls = new ArrayList<>();
        for (String unper : key) {
            unperls.add(unper);
        }
        return unperls.toArray(new String[unperls.size()]);
    }

    /**
     * 检测添加的权限是否在manifest中注册如果没有就删掉该权限
     */
    private String[] checkManifestPermission(final String[] permissions) {
        String[] manifestpers = FManifestUtils.getRegPermission();
        List<String> manigestls = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            for (int j = 0; j < manifestpers.length; j++) {
                if (permissions[i].equals(manifestpers[j])) {
                    manigestls.add(permissions[i]);
                }
            }
        }
        return manigestls.toArray(new String[manigestls.size()]);
    }

    /**
     * 获取请求权限中需要授权的权限
     */
    private String[] getDeniedPermissions(final String[] permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions.toArray(new String[deniedPermissions.size()]);
    }

    /**
     * 是否彻底拒绝了某项权限
     */
    public boolean hasAlwaysDeniedPermission(final String... deniedPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (!shouldShowRequestPermissionRationale(deniedPermission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否有权限需要说明提示
     */
    private boolean shouldShowRequestPermissionRationale(final String... deniedPermissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;
        boolean rationale;
        for (String permission : deniedPermissions) {
            rationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            if (rationale) return true;
        }
        return false;
    }

    public interface OnPermissionListener {

        void onPermissionGranted();

        void onPermissionDenied(String[] deniedPermissions);

        void manifestUnPermission(String[] unpermission);
    }

    public abstract class RationaleHandler {
        private Context context;
        private int requestCode;
        private String[] permissions;

        protected abstract void showRationale();

        void showRationale(Context context, int requestCode, String[] permissions) {
            this.context = context;
            this.requestCode = requestCode;
            this.permissions = permissions;
            showRationale();
        }

        /**
         * 重新请求权限
         */
        public void requestPermissionsAgain() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ((Activity) context).requestPermissions(permissions, requestCode);
            }
        }
    }


}
