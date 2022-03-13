package top.iqqcode.refreshheader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import top.iqqcode.refreshheader.R;
import top.iqqcode.refreshheader.interfaces.IRefreshHeader;
import top.iqqcode.refreshheader.view.RefreshHeaderRecyclerView;

/**
 * @Author: iqqcode
 * @Date: 2022-03-12 23:09
 * @Description:
 */
public class CommonAdapter extends RefreshHeaderRecyclerView.Adapter<CommonAdapter.ViewHolder> {

    private static final int TYPE_REFRESH_HEADER = 100000;
    private static final int TYPE_NORMAL = 10001;

    private final Context mContext;
    private List<String> mList;
    private IRefreshHeader mRefreshHeader;
    private OnItemClickListener onClickListener;

    public CommonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<String> data) {
        this.mList = data;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.onClickListener = clickListener;
    }

    /**
     * 来创建新item view
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_REFRESH_HEADER) {
            // view = LayoutInflater.from(mContext).inflate(R.layout.refresh_header_item, parent, false);
            view = mRefreshHeader.getHeaderView();
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        }
        return new ViewHolder(view);
    }

    /**
     * 将数据与视图绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder != null && holder.textView != null) {
            holder.textView.setText(mList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "item click", Toast.LENGTH_SHORT).show();
                    onClickListener.onItemClick(holder.getAdapterPosition());
                }
            });
        }
    }

    /**
     * 获得需要显示数据的总数
     * @return
     */
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 根据不同的position可以返回不同的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // 当position位置为0时，返回为头部的类型
        if (position == 0) {
            return TYPE_REFRESH_HEADER;
        }
        return TYPE_NORMAL;
    }

    public void setRefreshHeader(IRefreshHeader header) {
        if (header != null) {
            mRefreshHeader = header;
        }
    }

    class ViewHolder extends RefreshHeaderRecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_content);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
