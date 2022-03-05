package top.iqqcode.app2.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import top.iqqcode.app2.R;
import top.iqqcode.app2.beans.MoreTypeBean;

/**
 * @Author: iqqcode
 * @Date: 2021-04-22 09:49
 * @Description:
 */

public class MoreTypeAdapter extends RecyclerView.Adapter {

    private List<MoreTypeBean> mList;

    // 定义三个常量表示，因为item有三种类型
    public final static int TYPE_FULL_IMAGE = 0; // 大图
    public final static int TYPE_RIGHT_IMAGE = 1; // 左字右图
    public final static int TYPE_THREE_IMAGES = 2; // 三个图片


    // 初始化数据
    public MoreTypeAdapter(List<MoreTypeBean> list) {
        this.mList = list;
    }

    /**
     * 根据 viewType返回不同类型的界面
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 根据ViewType来创建条目，条目样式就不同了
        View view = null;

        if (viewType == TYPE_FULL_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_full_image, null);
            return new FullImageHolder(view);
        } else if (viewType == TYPE_RIGHT_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_left_text_right_image, null);
            return new RightImageHolder(view);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_type_three_image, null);
            return new ThreeImageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 覆写方法，这个方法是根据条件来返回条目类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        MoreTypeBean moreTypeBean = mList.get(position);
        if (moreTypeBean.type == 0) {
            return TYPE_FULL_IMAGE;
        } else if (moreTypeBean.type == 1) {
            return TYPE_RIGHT_IMAGE;
        } else {
            return TYPE_THREE_IMAGES;
        }
    }

    /**
     * 大图
     **/
    private class FullImageHolder extends RecyclerView.ViewHolder {

        public FullImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * 左字右图
     **/
    private class RightImageHolder extends RecyclerView.ViewHolder {

        public RightImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * 三图
     **/
    private class ThreeImageHolder extends RecyclerView.ViewHolder {

        public ThreeImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
