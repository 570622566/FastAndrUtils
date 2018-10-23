package cn.hotapk.fastandrutils.recyclerView;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.Collections;
import java.util.List;

/**
 * @author laijian
 * @version 2018/7/4
 * @Copyright (C)下午3:29 , www.hotapk.cn
 * @since
 */
public class FBaseRvAdapter<T> extends RecyclerView.Adapter<FViewHolder> {

    protected FItemTypeDelegateManager<T> fItemTypeDelegateManager;

    protected List<T> datas = Collections.EMPTY_LIST;
    private Context context;
    private AdapterView.OnItemClickListener onItemClickListener;
    private AdapterView.OnItemLongClickListener onItemLongClickListener;
    private int headerSize = 0;
    private int footerSize = 0;

    public FBaseRvAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
        fItemTypeDelegateManager = new FItemTypeDelegateManager<>();
    }

    public int getHeaderSize() {
        return headerSize;
    }

    public int getFooterSize() {
        return footerSize;
    }

    public void addItemTypeDelegate(FItemTypeDelegate<T> fItemTypeDelegate) {
        fItemTypeDelegateManager.addDelegate(fItemTypeDelegate);
    }

    public void addHeader(@LayoutRes int headerViewRes, final HeaderConvert headerConvert) {
        fItemTypeDelegateManager.addDelegate(new FHeaderTypeDelegate<T>(headerSize, headerViewRes) {
            @Override
            void headerConvert(FViewHolder holder, int position) {
                if (headerConvert != null) {
                    headerConvert.headerConvert(holder, position);
                }
            }
        });
        headerSize++;
    }

    public void addFooter(@LayoutRes int footerViewRes, final FooterConvert footerConvert) {
        fItemTypeDelegateManager.addDelegate(new FFooterTypeDelegate<T>(footerSize, footerViewRes) {
            @Override
            void footerConvert(FViewHolder holder, int position) {
                if (footerConvert != null) {
                    footerConvert.footerConvert(holder, position);
                }
            }
        });
        footerSize++;
    }


    public boolean checkRealData(int position) {
        return (position < headerSize) || position > (datas.size() - 1) + headerSize;
    }

    @Override
    public int getItemViewType(int position) {
        if (checkRealData(position)) {
            return fItemTypeDelegateManager.getItemViewType(null, position);
        }
        return fItemTypeDelegateManager.getItemViewType(datas.get(position - headerSize), position);
    }

    @Override
    public FViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FViewHolder.createViewHolder(context, parent, fItemTypeDelegateManager.getViewResouce(viewType));
    }

    @Override
    public void onBindViewHolder(final FViewHolder holder, int position) {
        T data = checkRealData(position) ? null : datas.get(position - headerSize);
        fItemTypeDelegateManager.onBindViewHolder(holder, data, position, getItemViewType(position));
        if (onItemClickListener != null) {
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkRealData(holder.getLayoutPosition())) {
                        return;
                    }
                    onItemClickListener.onItemClick(null, v, holder.getLayoutPosition() - headerSize, holder.getItemId());
                }
            });
            holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (checkRealData(holder.getLayoutPosition())) {
                        return false;
                    }
                    if (onItemLongClickListener != null) {
                        return onItemLongClickListener.onItemLongClick(null, v, holder.getLayoutPosition() - headerSize, holder.getItemId());
                    }
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size() + headerSize + footerSize;
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }


    public interface HeaderConvert {
        void headerConvert(FViewHolder holder, int position);
    }

    public interface FooterConvert {
        void footerConvert(FViewHolder holder, int position);
    }

}
