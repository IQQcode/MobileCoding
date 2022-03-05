package top.iqqcode.app2.adapter;

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
 * @Date: 2021-04-21 15:35
 * @Description:
 */
public class StaggeredGridAdapter extends RecyclerBaseAdapter {

    public StaggeredGridAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_staggered_view, null);
        return view;
    }
}
