package top.iqqcode.listviewadvanced.multiply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import top.iqqcode.listviewadvanced.R;
import top.iqqcode.listviewadvanced.multiply.bean.BotData;
import top.iqqcode.listviewadvanced.multiply.bean.FriendData;

/**
 * @Author: jiazihui
 * @Date: 2022-12-04 19:44
 * @Description:
 */
public class MultiplyAdapter extends BaseAdapter {

    /**
     * 机器人
     */
    public static final int TYPE_BOT = 1;

    /**
     * 好友
     */
    public static final int TYPE_FRIEND = 2;

    private Context mContext;
    @NonNull
    private List<BotData> mBotList = new ArrayList<>();
    @NonNull
    private List<FriendData> mFriendList = new ArrayList<>();

    public MultiplyAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<BotData> botList, List<FriendData> friendList) {
        mBotList.clear();
        mBotList.addAll(botList);

        mFriendList.clear();
        mFriendList.addAll(friendList);

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mBotList.size() + mFriendList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position <= mBotList.size() - 1) {
            return TYPE_BOT;
        } else {
            return TYPE_FRIEND;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //声明一下viewHolder
        FriendViewHolder friendHolder;
        BotViewHolder botHolder;
        switch (getItemViewType(position)) {
            case TYPE_FRIEND:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_multiple_style02, null, false);
                    friendHolder = new FriendViewHolder(convertView);
                    convertView.setTag(friendHolder);
                } else {
                    friendHolder = (FriendViewHolder) convertView.getTag();
                }
                friendHolder.setHolderData(mFriendList, position);
                break;
            case TYPE_BOT:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_multiple_style01, null, false);
                    botHolder = new BotViewHolder(convertView);
                    convertView.setTag(botHolder);
                } else {
                    botHolder = (BotViewHolder) convertView.getTag();
                }
                botHolder.setHolderData(mBotList, position - mFriendList.size() + 1);
                break;
            default:
                break;
        }
        return convertView;
    }


    // ============================  ViewHolder ======================

    static class FriendViewHolder {

        ImageView messageIndex;
        TextView text;
        ImageView next;

        public FriendViewHolder(View rootView) {
            initHolderView(rootView);
        }

        private void initHolderView(View rootView) {
            messageIndex = (ImageView) rootView.findViewById(R.id.message_item2_img);
            text = (TextView) rootView.findViewById(R.id.message_item2_text);
            next = (ImageView) rootView.findViewById(R.id.message_item2_back);
        }

        public void setHolderData(List<FriendData> list, int position) {
            // 赋值  通过这一步说明还是要建实体类的
            messageIndex.setImageResource(list.get(position).getMessageIndex());
            text.setText(list.get(position).getText());
            next.setImageResource(list.get(position).getNext());
        }
    }


    static class BotViewHolder {
        ImageView messageIndex;
        TextView name;
        TextView content;
        TextView time;

        public BotViewHolder(View rootView) {
            initHolderView(rootView);
        }

        private void initHolderView(View rootView) {
            messageIndex = (ImageView) rootView.findViewById(R.id.message_item1_img);
            name = (TextView) rootView.findViewById(R.id.message_item1_text1);
            content = (TextView) rootView.findViewById(R.id.message_item1_text2);
            time = (TextView) rootView.findViewById(R.id.message_item1_time);
        }

        public void setHolderData(List<BotData> list, int position) {
            messageIndex.setImageResource(list.get(position).getMessageIndex());
            name.setText(list.get(position).getName());
            content.setText(list.get(position).getContent());
            time.setText(list.get(position).getTime());
        }
    }
}
