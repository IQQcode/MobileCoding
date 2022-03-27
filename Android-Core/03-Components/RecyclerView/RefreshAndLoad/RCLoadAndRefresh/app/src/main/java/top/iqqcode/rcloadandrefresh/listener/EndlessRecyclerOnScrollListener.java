package top.iqqcode.rcloadandrefresh.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: iqqcode
 * @Date: 2022-03-10 23:08
 * @Description:RecyclerView滑动监听
 * 当滑动到最后一个Item的时候，显示加载更多UI并且开始请求下一页列表的数据
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    // 用来标记是否正在向上滑动
    private boolean isSlidingUpward = false;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (manager != null) {
            // 当不滑动时
            switch (newState) {
                //静止状态
                case RecyclerView.SCROLL_STATE_IDLE:
                    // 获取最后一个完全显示的itemPosition
                    int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int itemCount = manager.getItemCount();
                    // 判断是否滑动到了最后一个item，并且是向上滑动
                    if (lastItemPosition == (itemCount - 1) && isSlidingUpward) {
                        // 加载更多
                        onLoadMore();
                    }
                    break;
                // 拖动状态，手指按在屏幕上拖动时的状态
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    break;
                // 惯性滑动
                case RecyclerView.SCROLL_STATE_SETTLING:
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        /**
         * 当向上滑动的时候dy > 0
         * 向左滑动的时候dx > 0的; 反方向滑动则小于0 适用于横向滑动列表的监听
         */
        isSlidingUpward = dy > 0;
    }

    /**
     * 加载更多回调
     */
    public abstract void onLoadMore();
}
