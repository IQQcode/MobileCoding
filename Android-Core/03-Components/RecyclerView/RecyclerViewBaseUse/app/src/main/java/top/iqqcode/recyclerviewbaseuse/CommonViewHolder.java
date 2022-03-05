package top.iqqcode.recyclerviewbaseuse;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: iqqcode
 * @Date: 2022-03-05 16:09
 * @Description:实体类
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;

    public CommonViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.itemImage);
        textView = itemView.findViewById(R.id.itemContent);
    }
}
