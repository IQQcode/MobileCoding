package top.iqqcode.refreshrecyclerview.interfaces;

/**
 * @Author: iqqcode
 * @Date: 2022-03-13 00:22
 * @Description:
 */
public class RefreshStateEnum {

    // 正常状态
    public static final int STATE_NORMAL = 0;

    // 下拉的状态
    public static final int STATE_RELEASE_TO_REFRESH = 1;

    // 正在刷新的状态
    public static final int STATE_REFRESHING = 2;

    // 刷新完成的状态
    public static final int STATE_DONE = 3;
}
