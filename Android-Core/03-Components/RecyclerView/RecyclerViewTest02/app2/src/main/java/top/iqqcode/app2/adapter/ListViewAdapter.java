package top.iqqcode.app2.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import top.iqqcode.app2.R;
import top.iqqcode.app2.beans.ItemBean;

/**
 * @Author: iqqcode
 * @Date: 2021-04-21 11:14
 * @Description:ListViewAdapter代码重构
 */
public class ListViewAdapter extends RecyclerBaseAdapter {

    public static final int TYPE_NORMAL = 0; // 普通的条目类型
    public static final int TYPE_LOADER_MORE = 1; // 加载更多

    private OnRefreshListener mUpPullRefreshListener;

    public ListViewAdapter(List<ItemBean> data) {
        super(data);
    }

    /**
     * 根据类型来创建view
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_NORMAL) {
            view = View.inflate(parent.getContext(), R.layout.item_list_view, null);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_list_load_more, null);
        }
        return view;
    }

    /**
     * 根据item的类型返回Holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent, viewType);
        if (viewType == TYPE_NORMAL) {
            return new InnerHolder(view);
        } else {
            return new LoadMoreHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL && holder instanceof InnerHolder) {
            // 在这里设置数据
            ((InnerHolder) holder).setData(data.get(position));
        } else if (getItemViewType(position) == TYPE_LOADER_MORE && holder instanceof LoadMoreHolder) {
            ((LoadMoreHolder) holder).updateLoadingBottom(LoadMoreHolder.LOAD_STATE_LOADING);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            // 最后一个则返回更多
            return TYPE_LOADER_MORE;
        }
        return TYPE_NORMAL;
    }

    public class LoadMoreHolder extends RecyclerView.ViewHolder {

        public static final int LOAD_STATE_LOADING = 0; // 底部加载中
        public static final int LOAD_STATE_RELOAD = 1; // 重新加载
        public static final int LOAD_STATE_NORMAL = 2; // 加载成功

        private LinearLayout loadBottom;
        private TextView reloadBottom;

        public LoadMoreHolder(@NonNull View itemView) {
            super(itemView);
            loadBottom = itemView.findViewById(R.id.loading_bottom);
            reloadBottom = itemView.findViewById(R.id.tv_reloading);

            reloadBottom.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    updateLoadingBottom(LOAD_STATE_LOADING);
                }
            });
        }

        public void updateLoadingBottom(int state) {

            loadBottom.setVisibility(View.GONE);
            reloadBottom.setVisibility(View.GONE);

            switch (state) {
                case LOAD_STATE_LOADING:
                    loadBottom.setVisibility(View.VISIBLE);
                    // 触发加载数据
                    startLoadingMore();
                    break;
                case LOAD_STATE_RELOAD:
                    reloadBottom.setVisibility(View.VISIBLE);
                    // 触发加载数据
                    break;
                case LOAD_STATE_NORMAL:
                    loadBottom.setVisibility(View.GONE);
                    reloadBottom.setVisibility(View.GONE);
                    break;
                default:
                    throw new RuntimeException("下拉刷新加载错误哦~");
            }
        }

        public void startLoadingMore() {
            if (mUpPullRefreshListener != null) {
                mUpPullRefreshListener.onUpPullRefresh(this);
            }
        }
    }

    // 定义接口
    public interface OnRefreshListener {
        void onUpPullRefresh(LoadMoreHolder loadMoreHolder);
    }

    /**
     * 设置属性监听的接口
     * @param onRefreshListener
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mUpPullRefreshListener = onRefreshListener;
    }
}
