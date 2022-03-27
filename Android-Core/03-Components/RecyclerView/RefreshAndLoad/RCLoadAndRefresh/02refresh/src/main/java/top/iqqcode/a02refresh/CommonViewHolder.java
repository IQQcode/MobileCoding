package top.iqqcode.a02refresh;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: iqqcode
 * @Date: 2022-03-12 17:27
 * @Description:
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
