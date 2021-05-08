package top.iqqcode.a01netwebview;

import android.content.Context;
import android.content.Intent;

/**
 * @Author: iqqcode
 * @Date: 2021-05-19 18:57
 * @Description:
 */
public class NetJavaScriptInterface {

    Context context;

    public NetJavaScriptInterface(Context context) {
        this.context = context;
    }

    @android.webkit.JavascriptInterface
    public void openImage(String img, String[] imgs) {
        int size = imgs.length;
        int position = 0;
        for (int i = 0; i < size; i++) {
            if (img.equals(imgs[i])) {
                position = i;
            }
        }
        //跳转图片界面
        Intent intent = new Intent(context, DisplayImageActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("imgs", imgs);
        context.startActivity(intent);
    }
}
