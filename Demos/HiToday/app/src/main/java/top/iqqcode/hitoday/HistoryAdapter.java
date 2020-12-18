package top.iqqcode.hitoday;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.squareup.picasso.Picasso;

import java.util.List;

import top.iqqcode.hitoday.bean.HistoryBean;

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 23:25
 * @Description:
 */
public class HistoryAdapter extends BaseAdapter {

    Context context = null;
    List<HistoryBean.ResultBean> resultList;

    public HistoryAdapter(Context context, List<HistoryBean.ResultBean> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Object getItem(int position) {
        return resultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 为空创建控件
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main_timeline, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 得到指定位置的数据源
        HistoryBean.ResultBean resultBean = resultList.get(position);

        // 判断当前位置的年份和上一个位置是否相同
        if (position != 0) {
            HistoryBean.ResultBean lastBean = resultList.get(position - 1);
            if (resultBean.getYear() == lastBean.getYear()) {
                holder.timeLayout.setVisibility(View.GONE);
            } else {
                holder.timeLayout.setVisibility(View.VISIBLE);
            }
        } else {
            holder.timeLayout.setVisibility(View.VISIBLE);
        }
        // 当前年份下要显示的内容
        holder.timeView.setText(resultBean.getYear() + "-" + resultBean.getMonth() + "-" + resultBean.getDay());
        holder.tittleView.setText(resultBean.getTitle());
        // 图片URL
        String picURL = resultBean.getPic();
        // 有图片则显示
        if (TextUtils.isEmpty(picURL)) {
            // 图片地址为空则不显示
            holder.picImageView.setVisibility(View.GONE);
        } else {
            holder.picImageView.setVisibility(View.VISIBLE);
            // 将获取到的图片加载出来
            Picasso.with(context).load(picURL).into(holder.picImageView);
        }
        return convertView;
    }

    class ViewHolder {
        TextView timeView, tittleView;
        ImageView picImageView;
        LinearLayout timeLayout;

        public ViewHolder(View itemView) {
            timeView = itemView.findViewById(R.id.item_main_time);
            tittleView = itemView.findViewById(R.id.item_main_tittle);
            picImageView = itemView.findViewById(R.id.item_main_pic);
            timeLayout = itemView.findViewById(R.id.item_main_date);
        }
    }
}
