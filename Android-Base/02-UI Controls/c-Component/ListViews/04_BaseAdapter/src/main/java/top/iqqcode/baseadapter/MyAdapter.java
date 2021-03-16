package top.iqqcode.baseadapter;

import android.content.Context;
import android.graphics.Bitmap;
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
 * @Date: 2021-03-16 10:38
 * @Description:自定义Adapter
 */
public class MyAdapter extends BaseAdapter {

    List<Map<String, Object>> list;

    // 反射器: 将item的XML布局文件反射成为View
    LayoutInflater inflater;

    public MyAdapter(Context context) {
        // 初始化上下文
        this.inflater = LayoutInflater.from(context);
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // 返回当前item所在的位置(Map所在位置)
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 系统渲染的每一行item
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // [二级优化] ViewHolder
        ViewHolder holder = null;

        // [一级优化] convertView减少inflate的次数
        if (convertView == null) {

            Log.e("TAG", "getView() *** position = " + position + " convertView = " + convertView);

            // 此处传递this是Adapter的对象
            // 反射器: 将item的XML布局文件反射成为View
            // 1. 拿到view视图对象，从item布局文件反射器获取而来(XML文件映射成为内存中实际存在的View对象)
            // 父容器对象不需要传递，因为仅仅是展示
            // View view = inflater.inflate(R.layout.item_view, null);
            convertView = inflater.inflate(R.layout.item_view, null);

            // 2. 获取控件
            holder = new ViewHolder();
            holder.logo = convertView.findViewById(R.id.logo);
            holder.app_name = convertView.findViewById(R.id.app_name);
            holder.app_version = convertView.findViewById(R.id.app_version);
            holder.app_package = convertView.findViewById(R.id.app_package);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 每个item加载的内容是不同的，所以需要重复获取(无法优化)
        holder.logo.setImageResource((Integer) list.get(position).get("logo"));
        holder.app_name.setText((String) list.get(position).get("title"));
        holder.app_version.setText((String) list.get(position).get("version"));
        holder.app_package.setText((String) list.get(position).get("size"));

        Button btn = convertView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("spl", "点击");
            }
        });

        // 返回实实在在的view供系统渲染item
        return convertView;
    }
}