package top.iqqcode.recyclerview01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 17:03
 * @Description:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Fruit> list;
    private LayoutInflater inflater;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fruit_image);
            textView = itemView.findViewById(R.id.fruit_name);
        }
    }

    // 初始化Data
    public MyAdapter(Context context, List<Fruit> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 创建ViewHolder实例
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_view, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * 对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Fruit fruit = list.get(position);
        holder.imageView.setImageResource(fruit.getImageId());
        holder.textView.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
