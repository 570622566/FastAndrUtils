package cn.hotapk.fastandrutils.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.hotapk.fastandrutils.fragment.FShareDialog;


/**
 * @author laijian
 * @version 2018/10/19
 * @Copyright (C)10:37 AM , www.hotapk.cn
 */
public class FShare {

    private FragmentActivity mActivity;
    private String[] shareFilter;
    private String filePath = "";
    private String topTitle = "";
    private String shareContent = "";
    private String shareSubject = "";
    private String authorities = "";
    private int requestCode = -1;
    private List<ComponentName> componentNames = new ArrayList<>();
    private FShareBuilder fShareBuilder;


    public FShare(FShareBuilder fShareBuilder) {
        this.fShareBuilder = fShareBuilder;
        this.mActivity = fShareBuilder.mActivity;
        this.shareFilter = fShareBuilder.shareFilter;
        this.filePath = fShareBuilder.filePath;
        this.topTitle = fShareBuilder.topTitle;
        this.shareContent = fShareBuilder.shareContent;
        this.shareSubject = fShareBuilder.shareSubject;
        this.authorities = fShareBuilder.authorities;
        this.requestCode = fShareBuilder.requestCode;
        this.componentNames = fShareBuilder.componentNames;

    }


    private Intent getIntent(ComponentName componentName, String mimeType, Uri uri) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        if (componentName != null) {
            shareIntent.setComponent(componentName);
        }
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        shareIntent.setType(mimeType);
        if (uri != null) {
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        }
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        return shareIntent;
    }


    public void shareByCustom() {
        FShareDialog fShareDialog = FShareDialog.newInstance(fShareBuilder);
        fShareDialog.show(mActivity.getSupportFragmentManager(), "share");
    }

    public void shareBySystem() {
        Uri uri = null;
        Intent chooserIntent;
        String mimeType = "text/plain";
        if (!TextUtils.isEmpty(filePath)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(mActivity, authorities, new File(filePath));
            } else {
                uri = Uri.fromFile(new File(filePath));
            }
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.getPath()));
        }
        if (componentNames.isEmpty()) {
            Intent shareIntent = getIntent(null, mimeType, uri);
            chooserIntent = Intent.createChooser(shareIntent, TextUtils.isEmpty(topTitle) ? "分享到：" : topTitle);
        } else {
            List<Intent> intents = new ArrayList<>();
            for (int i = 0; i < componentNames.size(); i++) {
                intents.add(getIntent(componentNames.get(i), mimeType, uri));
            }
            // 选择分享时的标题
            chooserIntent = Intent.createChooser(intents.remove(0), TextUtils.isEmpty(topTitle) ? "分享到：" : topTitle);
            if (chooserIntent == null) {
                return;
            }
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.toArray(new Parcelable[]{}));
        }

        try {
            if (requestCode != -1) {
                mActivity.startActivity(chooserIntent);
            } else {
                mActivity.startActivityForResult(chooserIntent, requestCode);
            }
        } catch (android.content.ActivityNotFoundException ex) {
        }


    }

    public static class FShareBuilder implements Serializable {

        private FragmentActivity mActivity;
        private String[] shareFilter;
        private String filePath = "";
        private String topTitle = "";
        private String shareContent = "";
        private String shareSubject = "";
        private String authorities = "";
        private int requestCode = -1;
        private List<ComponentName> componentNames = new ArrayList<>();


        public FShareBuilder(FragmentActivity mActivity, String authorities) {
            this.mActivity = mActivity;
            this.authorities = authorities;
        }

        public FShareBuilder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }


        public FShareBuilder setTopTitle(String topTitle) {
            this.topTitle = topTitle;
            return this;
        }

        public FShareBuilder setShareContent(String shareContent) {
            this.shareContent = shareContent;
            return this;
        }

        public FShareBuilder setShareSubject(String shareSubject) {
            this.shareSubject = shareSubject;
            return this;
        }

        public String[] getShareFilter() {
            return shareFilter;
        }

        public FShareBuilder setShareFilter(String[] shareFilter) {
            this.shareFilter = shareFilter;
            return this;
        }

        public FShareBuilder setRequestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public FShareBuilder setComponentNames(List<ComponentName> componentNames) {
            this.componentNames = componentNames;
            return this;
        }


        public String getFilePath() {
            return filePath;
        }

        public String getTopTitle() {
            return topTitle;
        }

        public String getShareContent() {
            return shareContent;
        }

        public String getShareSubject() {
            return shareSubject;
        }

        public String getAuthorities() {
            return authorities;
        }

        public int getRequestCode() {
            return requestCode;
        }

        public List<ComponentName> getComponentNames() {
            return componentNames;
        }

        public FShare build() {
            return new FShare(this);
        }


    }
}
