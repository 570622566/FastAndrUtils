package cn.hotapk.fastandrutils.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import cn.hotapk.fastandrutils.R;
import cn.hotapk.fastandrutils.widget.FButton;

/**
 * @author laijian
 * @version 2018/7/2
 * @Copyright (C)下午4:24 , www.hotapk.cn
 * @since
 */
public class FAlertDialog extends FBaseDialogFragment {

    public static final String FDIALOG_TITLETEXTSIZE = "FDIALOG_TITLETEXTSIZE";
    public static final String FDIALOG_CONTENTTEXTSIZE = "FDIALOG_CONTENTTEXTSIZE";
    public static final String FDIALOG_LEFTTEXTSIZE = "FDIALOG_LEFTTEXTSIZE";
    public static final String FDIALOG_RIGHTTEXTSIZE = "FDIALOG_RIGHTTEXTSIZE";
    public static final String FDIALOG_TITLETEXTCOLOR = "FDIALOG_TITLETEXTCOLOR";
    public static final String FDIALOG_CONTENTTEXTCOLOR = "FDIALOG_CONTENTTEXTCOLOR";
    public static final String FDIALOG_LEFTTEXTCOLOR = "FDIALOG_LEFTTEXTCOLOR";
    public static final String FDIALOG_RIGHTTEXTCOLOR = "FDIALOG_RIGHTTEXTCOLOR";
    public static final String FDIALOG_HIDELEFTBT = "FDIALOG_HIDELEFTBT";
    public static final String FDIALOG_TITLETEXT = "FDIALOG_TITLETEXT";
    public static final String FDIALOG_CONTENTTEXT = "FDIALOG_CONTENTTEXT";
    public static final String FDIALOG_LEFTTEXT = "FDIALOG_LEFTTEXT";
    public static final String FDIALOG_RIGHTTEXT = "FDIALOG_RIGHTTEXT";
    public static final String FDIALOG_VISIBILITY = "FDIALOG_VISIBILITY";

    private TextView dialogTitle;
    private TextView dialogContent;
    private FButton leftBt;
    private FButton rightBt;

    private OnLeftClickListener onLeftClickListener;
    private OnRightClickListener onRightClickListener;


    private float titleTextSize;
    private float contentTextSize;
    private float leftTextSize;
    private float rightTextSize;

    private String titleText;
    private String contentText;
    private String leftText;
    private String rightText;

    private int titleTextColor;
    private int contentTextColor;
    private int leftTextColor;
    private int rightTextColor;

    private int visibilityTitle;
    private int visibilityLeft;


    @Override
    protected int setContentView() {
        return R.layout.f_alertdialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            titleTextSize = bundle.getFloat(FAlertDialog.FDIALOG_TITLETEXTSIZE);
            contentTextSize = bundle.getFloat(FAlertDialog.FDIALOG_CONTENTTEXTSIZE);
            leftTextSize = bundle.getFloat(FAlertDialog.FDIALOG_LEFTTEXTSIZE);
            rightTextSize = bundle.getFloat(FAlertDialog.FDIALOG_RIGHTTEXTSIZE);

            titleText = bundle.getString(FAlertDialog.FDIALOG_TITLETEXT);
            contentText = bundle.getString(FAlertDialog.FDIALOG_CONTENTTEXT);
            leftText = bundle.getString(FAlertDialog.FDIALOG_LEFTTEXT);
            rightText = bundle.getString(FAlertDialog.FDIALOG_RIGHTTEXT);

            titleTextColor = bundle.getInt(FAlertDialog.FDIALOG_TITLETEXTCOLOR);
            contentTextColor = bundle.getInt(FAlertDialog.FDIALOG_CONTENTTEXTCOLOR);
            leftTextColor = bundle.getInt(FAlertDialog.FDIALOG_LEFTTEXTCOLOR);
            rightTextColor = bundle.getInt(FAlertDialog.FDIALOG_RIGHTTEXTCOLOR);

            visibilityLeft = bundle.getInt(FAlertDialog.FDIALOG_HIDELEFTBT);
            visibilityTitle = bundle.getInt(FAlertDialog.FDIALOG_VISIBILITY);
        }

    }

    @Override
    public void initView(View view) {
        dialogTitle = (TextView) view.findViewById(R.id.dialog_title);
        dialogContent = (TextView) view.findViewById(R.id.dialog_content);
        leftBt = (FButton) view.findViewById(R.id.left_bt);
        rightBt = (FButton) view.findViewById(R.id.right_bt);
        init();
        onclick();
    }

    private void init() {
        dialogTitle.setText(titleText);
        dialogTitle.setTextSize(titleTextSize);
        dialogTitle.setTextColor(ContextCompat.getColor(getContext(), titleTextColor));
        dialogTitle.setVisibility(visibilityTitle);

        dialogContent.setTextColor(ContextCompat.getColor(getContext(), contentTextColor));
        dialogContent.setText(contentText);
        dialogContent.setTextSize(contentTextSize);

        leftBt.setText(leftText);
        leftBt.setTextSize(leftTextSize);
        leftBt.setTextColor(ContextCompat.getColor(getContext(), leftTextColor));
        leftBt.setVisibility(visibilityLeft);

        rightBt.setTextColor(ContextCompat.getColor(getContext(), rightTextColor));
        rightBt.setText(rightText);
        rightBt.setTextSize(rightTextSize);

    }

    private void onclick() {
        leftBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLeftClickListener != null) {
                    onLeftClickListener.onClick(FAlertDialog.this);
                } else {
                    dismiss();
                }
            }
        });
        rightBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightClickListener != null) {
                    onRightClickListener.onClick(FAlertDialog.this);
                } else {
                    dismiss();
                }
            }
        });
    }


    public void setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
    }

    public void setOnRightClickListener(OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
    }

    public interface OnLeftClickListener {
        void onClick(FAlertDialog fAlertDialog);
    }

    public interface OnRightClickListener {
        void onClick(FAlertDialog fAlertDialog);
    }
}
