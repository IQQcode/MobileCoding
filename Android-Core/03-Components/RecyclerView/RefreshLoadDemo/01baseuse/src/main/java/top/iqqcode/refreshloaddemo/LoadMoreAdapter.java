package top.iqqcode.refreshloaddemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2022-03-20 16:09
 * @Description:上拉加载更多
 */
public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private OnItemClickListener onClickListener;

    // 普通布局
    private final static int TYPE_NORMAL = 1;
    // Footer布局
    private final static int TYPE_FOOTER = 2;

    // 当前加载状态，默认为加载完成
    private int loadState = 2;
    // 正在加载
    public final static int LOADING = 1;
    // 加载完成
    public final static int LOADING_COMPLETE = 2;
    // 重新加载
    public static final int LOAD_RELOAD = 3;
    // 加载到底
    public final static int LOADING_END = 4;

    private List<String> dataList;

    public LoadMoreAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
        mContext = context;
    }

    public void setData(List<String> list) {
        dataList = list;
    }

    /**
     * 设置上拉加载状态
     *
     * @param loadState 0.正在加载 1.加载完成 2.加载到底
     */
    @SuppressLint("NotifyDataSetChanged")
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.onClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 通过判断显示类型，来创建不同的View
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_refresh_footer, parent, false);
            return new FootViewHolder(view);
        } else { // viewType == TYPE_ITEM
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new CommonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL && holder instanceof CommonViewHolder) {
            CommonViewHolder commonViewHolder = (CommonViewHolder) holder;
            commonViewHolder.textView.setText(dataList.get(position));
            commonViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "item click", Toast.LENGTH_SHORT).show();
                    onClickListener.onItemClick(holder.getAdapterPosition());
                }
            });
        } else if (getItemViewType(position) == TYPE_FOOTER && holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            // 处理加载状态
            footViewHolder.updateLoadMore(loadState);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为FooterView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size() + 1;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) { // 当前是否为网格布局
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    // 返回值决定了每个Item占据的单元格数
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }


    private static class CommonViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        CommonViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_content);
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llLoading;
        LinearLayout llEnd;

        TextView errorTipsView;

        FootViewHolder(View itemView) {
            super(itemView);
            llLoading = (LinearLayout) itemView.findViewById(R.id.ll_loading);
            llEnd = (LinearLayout) itemView.findViewById(R.id.ll_footer);
        }

        private void updateLoadMore(int state) {
            switch (state) {
                // 正在加载
                case LOADING:
                    llLoading.setVisibility(View.VISIBLE);
                    llEnd.setVisibility(View.GONE);
                    break;

                // 加载完成
                case LOADING_COMPLETE:
                    llLoading.setVisibility(View.INVISIBLE);
                    llEnd.setVisibility(View.GONE);
                    break;

                // 加载到底
                case LOADING_END:
                    llLoading.setVisibility(View.GONE);
                    llEnd.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
