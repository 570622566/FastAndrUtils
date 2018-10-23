package cn.hotapk.fastandrutils.fragment;


import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.hotapk.fastandrutils.R;
import cn.hotapk.fastandrutils.bean.ShareBean;
import cn.hotapk.fastandrutils.recyclerView.FSimpleRvAdapter;
import cn.hotapk.fastandrutils.recyclerView.FViewHolder;
import cn.hotapk.fastandrutils.utils.FShare;

public class FShareDialog extends FBaseDialogFragment {

    private TextView shareTitle;
    private RecyclerView shareLv;
    private TextView shareCancel;
    private List<ShareBean> shareBeans = new ArrayList<>();
    private FSimpleRvAdapter<ShareBean> sharesAdapter;
    private String mimeType = "text/plain";
    private String filePath = "";
    private String authorities = "";
    private String[] shareFilter;
    private String topTitle = "";
    private String shareContent = "";
    private String shareSubject = "";
    private int requestCode = -1;
    private FShare.FShareBuilder fShareBuilder;

    private Uri uri = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        fShareBuilder = (FShare.FShareBuilder) arguments.getSerializable("fShareBuilder");
        filePath = fShareBuilder.getFilePath();
        authorities = fShareBuilder.getAuthorities();
        shareFilter = fShareBuilder.getShareFilter();
        topTitle = fShareBuilder.getTopTitle();
        shareContent = fShareBuilder.getShareContent();
        shareSubject = fShareBuilder.getShareSubject();
        requestCode = fShareBuilder.getRequestCode();

    }

    public FShareDialog() {
    }

    @Override
    protected int setContentView() {
        return R.layout.f_sharedialog;
    }

    public static FShareDialog newInstance(FShare.FShareBuilder fShareBuilder) {
        FShareDialog fragment = new FShareDialog();
        Bundle args = new Bundle();
        args.putInt(FBaseDialogFragment.FDIALOG_GRAVITY, Gravity.BOTTOM);
        args.putFloat(FBaseDialogFragment.FDIALOG_ALPHA, 0.5f);
        args.putSerializable("fShareBuilder", fShareBuilder);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(View view) {
        shareTitle = view.findViewById(R.id.share_title);
        shareLv = view.findViewById(R.id.share_lv);
        shareCancel = view.findViewById(R.id.share_cancel);
        shareTitle.setText(TextUtils.isEmpty(topTitle) ? "分享" : topTitle);
        shareLv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        sharesAdapter = new FSimpleRvAdapter<ShareBean>(getContext(), shareBeans, R.layout.share_item) {
            @Override
            public void convertHolder(FViewHolder fViewHolder, ShareBean shareBean, int i) {
                fViewHolder.setText(R.id.share_tv, shareBean.getAppName());
                fViewHolder.setImageDrawable(R.id.share_img, shareBean.getIcon());
            }
        };
        shareLv.setAdapter(sharesAdapter);
        sharesAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                Intent intent = getIntent(new ComponentName(shareBeans.get(position).getPackageName(), shareBeans.get(position).getClassName()), mimeType, uri);
                if (requestCode != -1) {
                    getActivity().startActivityForResult(intent, requestCode);
                } else {
                    getActivity().startActivity(intent);
                }
            }
        });

        shareCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        getShareApp();

    }

    @SuppressLint("CheckResult")
    private void getShareApp() {

        if (!TextUtils.isEmpty(filePath)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(getContext(), authorities, new File(filePath));
            } else {
                uri = Uri.fromFile(new File(filePath));
            }
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.getPath()));
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(mimeType);
        new ShareAsync().execute(getActivity().getPackageManager());
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

    class ShareAsync extends AsyncTask<PackageManager, Integer, List<ShareBean>> {

        @Override
        protected List<ShareBean> doInBackground(PackageManager... packageManagers) {
            List<ShareBean> shareBeans = new ArrayList<>();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType(mimeType);
            List<ResolveInfo> resolveInfos = packageManagers[0].queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);
            for (int i = 0; i < resolveInfos.size(); i++) {
                ResolveInfo resolveInfo = resolveInfos.get(i);
                PackageManager pm = getActivity().getApplication().getPackageManager();
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                ShareBean shareBean = new ShareBean();
                shareBean.setAppName(activityInfo.loadLabel(pm).toString());
                shareBean.setPackageName(activityInfo.packageName);
                shareBean.setClassName(activityInfo.name);
                shareBean.setIcon(activityInfo.loadIcon(pm));

                if (shareFilter != null && shareFilter.length > 0) {
                    for (int j = 0; j < shareFilter.length; j++) {
                        if (shareBean.getPackageName().equals(shareFilter[j])) {
                            shareBeans.add(shareBean);
                        }
                    }
                } else {
                    shareBeans.add(shareBean);
                }
            }

            return shareBeans;
        }

        @Override
        protected void onPostExecute(List<ShareBean> datas) {
            super.onPostExecute(datas);
            shareBeans.clear();
            shareBeans.addAll(datas);
            sharesAdapter.notifyDataSetChanged();
        }
    }


}
