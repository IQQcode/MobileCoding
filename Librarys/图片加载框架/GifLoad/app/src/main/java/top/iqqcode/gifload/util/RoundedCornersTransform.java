package top.iqqcode.gifload.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class RoundedCornersTransform extends BitmapTransformation {

    private final String ID = "com.bumptech.glide.transformations.FillSpace";
    private final byte[] ID_ByTES = ID.getBytes(CHARSET);

    private BitmapPool mBitmapPool;

    private float radius;
    private Context mContext;

    private float leftTop;
    private float rightTop;
    private float rightBottom;
    private float leftBottom;

    /**
     * @param context 上下文
     * @param radius  圆角幅度
     */
    public RoundedCornersTransform(Context context, float radius) {
        this.radius = radius;
    }

    public RoundedCornersTransform(Context context, float leftTop, float rightTop, float rightBottom, float leftBottom) {
        mContext = context;
        this.leftTop = leftTop;
        this.rightTop = rightTop;
        this.rightBottom = rightBottom;
        this.leftBottom = leftBottom;
    }

    @NonNull
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();
        Bitmap bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setHasAlpha(true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        RectF rect = new RectF(0f, 0f, width, height);

        float[] radii = new float[]{
                leftTop, leftTop, rightTop, rightTop,
                rightBottom, rightBottom, leftBottom, leftBottom};
        Path path = new Path();
        path.addRoundRect(rect, radii, Path.Direction.CW);
        canvas.drawPath(path, paint);
        return bitmap;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof RoundedCornersTransform) {
            RoundedCornersTransform other = (RoundedCornersTransform) o;
            return leftTop == other.leftTop && rightTop == other.rightTop
                    && leftBottom == other.leftBottom && rightBottom == other.rightBottom;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ID.hashCode() + Util.hashCode(leftTop) + Util.hashCode(rightTop) + Util.hashCode(leftBottom) + Util.hashCode(rightBottom);
        // return Util.hashCode(ID.hashCode(), Util.hashCode(leftTop));
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_ByTES);
        byte[] radiusData = ByteBuffer.allocate(4).putInt((int) radius).array();
        messageDigest.update(radiusData);
    }
}
