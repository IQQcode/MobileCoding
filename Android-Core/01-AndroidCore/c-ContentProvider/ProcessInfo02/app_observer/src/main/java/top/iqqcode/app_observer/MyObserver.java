package top.iqqcode.app_observer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/**
 * @Author: iqqcode
 * @Date: 2021-04-16 19:01
 * @Description:
 */
public class MyObserver extends ContentObserver {
    private Uri mUri = Uri.parse("content://top.iqqcode.processinfo.myprovider/insert");
    private ContentResolver mResolver;
    Handler mHandler;

    public MyObserver(Context context, Handler handler) {
        super(handler);
        mResolver = context.getContentResolver();
        mHandler = handler;
    }

    public MyObserver(Handler mHandler) {
        super(mHandler);
    }


    @Override
    public void onChange(boolean selfChange) {
        mHandler.sendEmptyMessage(0x111);
    }

    public void registerObserver() {
        mResolver.registerContentObserver(mUri, false, this);
    }

    public void unregisterObserver() {
        mResolver.unregisterContentObserver(this);
    }
}
