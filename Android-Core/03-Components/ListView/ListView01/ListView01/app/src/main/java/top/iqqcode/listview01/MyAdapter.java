package top.iqqcode.listview01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 15:33
 * @Description:
 */
public class MyAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;
    private Fruit fruit;

    public MyAdapter(@NonNull Context context, int resource, List<Fruit> fruitList) {
        super(context, resource, fruitList);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        fruit = getItem(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.fruit_image);
            holder.textView = convertView.findViewById(R.id.fruit_name);
            convertView.setTag(holder); // 将ViewHolder存储在View中
        } else {
            holder = (ViewHolder) convertView.getTag(); // 重新获取ViewHolder
        }
        holder.imageView.setImageResource(fruit.getImageId());
        holder.textView.setText(fruit.getName());
        return convertView;
    }

    class ViewHolder {

        ImageView imageView;

        TextView textView;

    }

}
