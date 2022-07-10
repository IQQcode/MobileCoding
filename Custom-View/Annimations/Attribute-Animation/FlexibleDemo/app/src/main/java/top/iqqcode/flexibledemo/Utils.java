package top.iqqcode.flexibledemo;

import android.content.Context;

/**
 * @Author: jiazihui
 * @Date: 2022-07-10 20:26
 * @Description:
 */
public class Utils {

    /**
     * dp 转成 px
     *
     * @param dpVale
     * @return
     */
    public static int dip2px(Context context, float  dpVale) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }

    /**
     * px 转成 dp
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
