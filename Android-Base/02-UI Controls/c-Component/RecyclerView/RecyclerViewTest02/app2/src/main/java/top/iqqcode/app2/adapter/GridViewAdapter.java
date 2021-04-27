package top.iqqcode.app2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import top.iqqcode.app2.R;
import top.iqqcode.app2.beans.ItemBean;

/**
 * @Author: iqqcode
 * @Date: 2021-04-21 11:14
 * @Description:GridViewAdapter代码重构
 */
public class GridViewAdapter extends RecyclerBaseAdapter {

    public GridViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_grid_view, null);
        return view;
    }
}
