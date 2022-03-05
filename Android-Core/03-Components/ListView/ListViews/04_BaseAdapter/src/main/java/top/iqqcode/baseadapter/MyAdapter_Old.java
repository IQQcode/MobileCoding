package top.iqqcode.baseadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

/**
 * @Author: iqqcode
 * @Date: 2021-03-16 15:42
 * @Description:MyAdapter优化前写法
 */
public class MyAdapter_Old extends BaseAdapter {

    /**
     * 数据集合
     */
    List<Map<String, Object>> list;
    /**
     * 反射器
     */
    LayoutInflater inflater;

    /**
     * 构造器
     *
     * @param context 上下文
     */
    public MyAdapter_Old(Context context) {
        inflater = LayoutInflater.from(context);
    }

    /**
     * 传入数据集合
     *
     * @param list
     */
    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_view, null);

        ImageView logo = convertView.findViewById(R.id.logo);
        TextView app_name = convertView.findViewById(R.id.app_name);
        TextView app_version = convertView.findViewById(R.id.app_version);
        TextView app_package = convertView.findViewById(R.id.app_package);

        logo.setImageResource((Integer) list.get(position).get("logo"));
        app_name.setText((String) list.get(position).get("title"));
        app_version.setText((String) list.get(position).get("version"));
        app_package.setText((String) list.get(position).get("size"));

        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("spl", "点击");
            }
        });

        return view;
    }
}