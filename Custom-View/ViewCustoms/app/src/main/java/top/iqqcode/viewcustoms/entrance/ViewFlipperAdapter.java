package top.iqqcode.viewcustoms.entrance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import top.iqqcode.viewcustoms.R;

public class ViewFlipperAdapter extends BaseAdapter {
    private final Context context;
    private List<ChatEntranceTestData> data;

    public ViewFlipperAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ChatEntranceTestData> data) {
        this.data = data;
        processData();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.carousel_demo_item, parent, false);
            viewHolder = new ViewFlipperAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewFlipperAdapter.ViewHolder) convertView.getTag();
        }
        processData();
        viewHolder.setHolderData(context, data.get(0));
        return convertView;
    }

    private void processData() {
        if (data == null) {
            return;
        }
        if (data.size() <= 1) {
            data.add(data.get(0));
        }
    }

    static class ViewHolder {

        public ImageView mHead;

        public TextView mTitleText;
        public TextView mContentText;

        public ViewHolder(View view) {
            this.mHead = view.findViewById(R.id.demo_chat_group_head_image);
            this.mTitleText = view.findViewById(R.id.demo_chat_group_name);
            this.mContentText = view.findViewById(R.id.demo_chat_group_content);
        }

        public void setHolderData(Context context, @NonNull ChatEntranceTestData data) {
            Glide.with(context).load(data.getUrl()).into(mHead);
            mTitleText.setText(data.getTitle());
            mContentText.setText(data.getContent());
        }
    }
}
