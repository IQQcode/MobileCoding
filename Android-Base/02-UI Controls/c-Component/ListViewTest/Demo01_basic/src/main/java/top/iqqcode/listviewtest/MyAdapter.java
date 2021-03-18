package top.iqqcode.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * @Author: iqqcode
 * @Date: 2021-03-16 22:07
 * @Description:
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

        ViewHolder holder = null;

        if (convertView == null) {
            // 1.拿到view视图对象，从item布局文件反射器获取而来(XML文件映射成为内存中实际存在的View对象)
            convertView = inflater.inflate(R.layout.item_view, null);
            // 2.获取控件
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.tv_title);
            holder.topic = convertView.findViewById(R.id.tv_topic);
            holder.time = convertView.findViewById(R.id.tv_time);
            holder.logo = convertView.findViewById(R.id.iv_logo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 每个item加载的内容是不同的，所以需要重复获取(无法优化)

        holder.title.setText((String) list.get(position).get("title"));
        holder.topic.setText((String) list.get(position).get("topic"));
        holder.time.setText((String) list.get(position).get("time"));
        holder.logo.setImageResource((Integer) list.get(position).get("logo"));

        // 返回实实在在的view供系统渲染item
        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView topic;
        TextView time;
        ImageView logo;
    }
}
