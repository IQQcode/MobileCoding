package top.iqqcode.refreshrecyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import top.iqqcode.refreshrecyclerview.adapter.CommonAdapter;
import top.iqqcode.refreshrecyclerview.interfaces.IRefreshHeader;
import top.iqqcode.refreshrecyclerview.interfaces.OnRefreshListener;

/**
 * @Author: iqqcode
 * @Date: 2022-03-13 11:22
 * @Description:带刷新View的RecyclerView二次封装
 */
public class RefreshHeaderRecyclerView extends RecyclerView {

    private float mLastY = -1;
    private float sumOffSet;
    private IRefreshHeader mRefreshHeader;
    private boolean mRefreshing = false;
    private CommonAdapter mHeaderAdapter;
    private OnRefreshListener mRefreshListener;

    public RefreshHeaderRecyclerView(Context context) {
        this(context,null);
    }

    public RefreshHeaderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshHeaderRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(CommonAdapter adapter) {
        if (adapter != null) {
            mHeaderAdapter = adapter;
            mRefreshHeader = new RefreshHeaderView(getContext().getApplicationContext());
            mHeaderAdapter.setRefreshHeader(mRefreshHeader);
        }
        super.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (mLastY == -1) {
            mLastY = e.getRawY();
        }
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = e.getRawY();
                sumOffSet = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaY = (e.getRawY() - mLastY) / 2;//为了防止滑动幅度过大，将实际手指滑动的距离除以2
                mLastY = e.getRawY();
                sumOffSet += deltaY;//计算总的滑动的距离
                if (isOnTop() && !mRefreshing) {
                    mRefreshHeader.onMove(deltaY, sumOffSet);//移动刷新的头部View
                    if (mRefreshHeader.getVisibleHeight() > 0) {
                        return false;
                    }
                }
                break;
            default:
                mLastY = -1; // reset
                if (isOnTop()&& !mRefreshing) {
                    if (mRefreshHeader.onRelease()) {
                        //手指松开
                        if (mRefreshListener != null) {
                            mRefreshing = true;
                            mRefreshListener.onRefresh();//回调刷新完成的监听
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(e);
    }

    private boolean isOnTop() {
        return mRefreshHeader.getHeaderView().getParent() != null;
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mRefreshListener = listener;
    }

    public void refreshComplete() {
        if (mRefreshing) {
            mRefreshing = false;
            mRefreshHeader.refreshComplete();

        }
    }
}
