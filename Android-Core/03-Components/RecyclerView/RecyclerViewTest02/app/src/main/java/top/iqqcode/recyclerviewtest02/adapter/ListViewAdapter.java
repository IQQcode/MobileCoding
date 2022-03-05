package top.iqqcode.recyclerviewtest02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import top.iqqcode.recyclerviewtest02.R;
import top.iqqcode.recyclerviewtest02.beans.ItemBean;

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 21:23
 * @Description:ListView效果的适配器
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private List<ItemBean> data;
    private LayoutInflater inflater;

    // 通过构造方法设置数据
    public ListViewAdapter(Context context, List<ItemBean> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 创建item的View
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_view, null, false);
        return new ViewHolder(view);
    }

    /**
     * 绑定holder, 用来设置数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    /**
     * 返回item个数
     * @return
     */
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.item_icon);
            title = itemView.findViewById(R.id.item_text);
        }

        public List<ItemBean> getData() {
            return data;
        }

        /**
         * 设置数据
         * @param itemBean
         */
        public void setData(ItemBean itemBean) {
            icon.setImageResource(itemBean.imageId);
            title.setText(itemBean.title);
        }
    }
}
