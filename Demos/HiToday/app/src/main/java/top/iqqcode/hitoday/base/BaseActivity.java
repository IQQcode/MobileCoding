package top.iqqcode.hitoday.base;

import androidx.appcompat.app.AppCompatActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 22:03
 * @Description:获取网络数据
 */
public class BaseActivity extends AppCompatActivity implements Callback.CommonCallback<String> {
    public void loadData(String url) {
        RequestParams requestParams = new RequestParams(url);
        // 当前类继承CommonCallback接口，当前对象属于CommonCallback的对象(向上转型)
        x.http().get(requestParams, this);
    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
