package top.iqqcode.a03baseadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;
import java.util.Map;

/**
 * @Author: iqqcode
 * @Date: 2021-04-30 21:53
 * @Description:
 */
public class MyBaseAdapter extends BaseAdapter {

    private List<AppInfo> list; // 乘方数据源的容器

    // 反射器: 将item的XML布局文件反射成为View
    private LayoutInflater inflater;

    // 生命接口
    public IClickButton clickButton;

    public void setClickButton(IClickButton clickButton) {
        this.clickButton = clickButton;
    }

    public MyBaseAdapter(Context context, List<AppInfo> list) {
        // 初始化上下文
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        // [二级优化] ViewHolder
        ViewHolder holder = null;

        // [一级优化] convertView减少inflate的次数
        if (convertView == null) {

            Log.e("TAG", "getView() *** position = " + position + " convertView = " + convertView);

            // 此处传递this是Adapter的对象
            // 反射器: 将item的XML布局文件反射成为View
            // 1. 拿到view视图对象，从item布局文件反射器获取而来(XML文件映射成为内存中实际存在的View对象)
            // 父容器对象不需要传递，因为仅仅是展示

            convertView = inflater.inflate(R.layout.item_view, null);

            // 2. 获取控件
            holder = new ViewHolder();
            holder.logo = convertView.findViewById(R.id.item_image);
            holder.app_name = convertView.findViewById(R.id.item_text_app_name);
            holder.app_size = convertView.findViewById(R.id.item_text_app_size);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 每个item加载的内容是不同的，所以需要重复获取(无法优化)

        AppInfo info = new AppInfo();
        holder.logo.setImageResource(list.get(position).getAppIcon());
        holder.app_name.setText(list.get(position).getAppName());
        holder.app_size.setText(list.get(position).getAppSize());

        // item中Button的点击事件
        Button btn = convertView.findViewById(R.id.item_btn_app);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.clickButton(position);
            }
        });

        // 返回实实在在的view供系统渲染item
        return convertView;
    }
}
