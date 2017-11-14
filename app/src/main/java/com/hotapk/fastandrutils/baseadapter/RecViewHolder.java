package com.hotapk.fastandrutils.baseadapter;

import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class RecViewHolder {
    private SparseArray<View> viewHolder;
    private View view;

    public static RecViewHolder getViewHolder(View view) {
        RecViewHolder viewHolder = (RecViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new RecViewHolder(view);
            view.setTag(viewHolder);
        }
        return viewHolder;
    }

    RecViewHolder(View view) {
        this.view = view;
        viewHolder = new SparseArray<View>();
        view.setTag(viewHolder);
    }

    public <T extends View> T get(int id) {
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    public View getConvertView() {
        return view;
    }

    public TextView getTextView(int id) {

        return get(id);
    }

    public RelativeLayout getRelativeLayout(int id) {

        return get(id);
    }

    public Button getButton(int id) {

        return get(id);
    }

    public ImageView getImageView(int id) {
        return get(id);
    }

    public void setTextView(int id, CharSequence charSequence) {
        getTextView(id).setText(charSequence);
    }

    public void setImagedrawable(int viewId, Drawable drawable) {
        getImageView(viewId).setImageDrawable(drawable);
    }

    public void setImageResource(int viewId, int Resourceid) {
        getImageView(viewId).setBackgroundResource(Resourceid);
    }


}
