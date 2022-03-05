package top.iqqcode.app2.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import top.iqqcode.app2.R;
import top.iqqcode.app2.beans.ItemBean;

/**
 * @Author: iqqcode
 * @Date: 2021-04-21 14:18
 * @Description:基类适配器
 */
public abstract class RecyclerBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<ItemBean> data;

    // 3. 提供设置接口的方法（其实是外部实现）
    private OnItemClickListener mOnItemClickListener;

    public RecyclerBaseAdapter(List<ItemBean> data) {
        this.data = data;
    }

    protected abstract View getSubView(ViewGroup parent, int viewType);


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent, viewType);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 在这里设置数据
        ((InnerHolder)holder).setData(data.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                    Log.e("TAG", "BaseAdapter click");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    // 2.设置item的监听事件(定义接口内部的方法)
    public void setOnItemClickListener(OnItemClickListener listener) {
        // 设置监听，即设置回调接口
        this.mOnItemClickListener = listener;
    }

    /**
     * 【编写回调的步骤】
     *   1. 创建这个接口
     *   2. 定义接口内部的方法
     *   3. 提供设置接口的方法（其实是外部实现）
     *   4. 接口方法的调用
     */

    // 1.创建接口
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView mIcon;
        private TextView mTitle;
        private int mPosition;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);

            // 初始化控件
            mIcon =  itemView.findViewById(R.id.icon);
            mTitle = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(mPosition);
                        Log.e("TAG", "click");
                    }
                }
            });
        }

        public void setData(ItemBean itemBean) {
            mIcon.setImageResource(itemBean.imageId);
            mTitle.setText(itemBean.title);
        }
    }
}
