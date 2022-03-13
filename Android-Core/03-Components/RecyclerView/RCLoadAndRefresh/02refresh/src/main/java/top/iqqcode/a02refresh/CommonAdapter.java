package top.iqqcode.a02refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2022-03-12 17:26
 * @Description:
 */
public class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private Context mContext;
    private List<ItemData> mList;
    private LayoutInflater inflater;
    private OnItemClickListener onClickListener;

    // 初始化Data
    public CommonAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<ItemData> list) {
        this.mList = list;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.onClickListener = clickListener;
    }

    /**
     * 创建ViewHolder实例
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view, null);
        CommonViewHolder holder = new CommonViewHolder(view);
        return holder;  // 传入的View就是条目界面
    }

    /**
     * 对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        ItemData itemData = mList.get(position);
        if (itemData != null) {
            holder.imageView.setImageResource(itemData.getImageId());
            holder.textView.setText(itemData.getName());
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
     * item条目个数
     * @return
     */
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
