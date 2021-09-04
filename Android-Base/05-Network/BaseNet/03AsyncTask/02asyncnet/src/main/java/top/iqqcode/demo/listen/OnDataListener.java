package top.iqqcode.demo.listen;

/**
 * @Author: iqqcode
 * @Date: 2021-08-14 20:41
 * @Description:
 */
public interface OnDataListener {

    void onDataSuccess(String data);

    void onDataFailed(String error);
}