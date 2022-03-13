package top.iqqcode.refreshrecyclerview.interfaces;

import android.view.View;

/**
 * @Author: iqqcode
 * @Date: 2022-03-13 00:22
 * @Description:
 */
public interface IRefreshHeader {

    /**
     * 恢复到正常状态
     */
    void onReset();

    /**
     * 处于可以刷新的状态，已经过了指定距离
     */
    void onPrepare();

    /**
     * 正在刷新
     */
    void onRefreshing();

    /**
     * 下拉移动
     */
    void onMove(float offSet, float sumOffSet);

    /**
     * 下拉松开
     */
    boolean onRelease();

    /**
     * 下拉刷新完成
     */
    void refreshComplete();

    /**
     * 获取HeaderView
     */
    View getHeaderView();

    /**
     * 获取Header的显示高度
     */
    int getVisibleHeight();
}
