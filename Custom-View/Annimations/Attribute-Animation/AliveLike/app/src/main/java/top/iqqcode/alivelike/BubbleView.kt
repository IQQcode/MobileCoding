package top.iqqcode.alivelike

import android.animation.*
import android.widget.RelativeLayout
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import java.util.ArrayList

/**
 * @Author: jiazihui
 * @Date: 2022-06-26 11:49
 * @Description:
 */
/**
 *
 */
class BubbleView : RelativeLayout {
    private var drawableList: List<Drawable> = ArrayList()
    private val riseDuration = 3000
    private val bottomPadding = 0
    private val originsOffset = 20
    private val maxScale = 1.0f
    private val minScale = 1.0f

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
    }

    private fun setDrawableList(drawableList: List<Drawable>): BubbleView {
        this.drawableList = drawableList
        return this
    }

    fun setDefaultDrawableList(): BubbleView {
        val drawableList: MutableList<Drawable> = ArrayList()
        drawableList.add(resources.getDrawable(R.mipmap.daisy))
        drawableList.add(resources.getDrawable(R.mipmap.heart))
        drawableList.add(resources.getDrawable(R.mipmap.mushroom))
        drawableList.add(resources.getDrawable(R.mipmap.palette))
        drawableList.add(resources.getDrawable(R.mipmap.unicorn))
        drawableList.add(resources.getDrawable(R.mipmap.watermelon))
        setDrawableList(drawableList)
        return this
    }

    fun startAnimation(rankWidth: Int, rankHeight: Int) {
        bubbleAnimation(rankWidth, rankHeight)
    }

    fun startAnimation(rankWidth: Int, rankHeight: Int, count: Int) {
        for (i in 0 until count) {
            bubbleAnimation(rankWidth, rankHeight)
        }
    }

    private fun bubbleAnimation(rankWidth: Int, rankHeight: Int) {
        var rankWidth = rankWidth
        var rankHeight = rankHeight
        rankHeight -= dip2px(context, bottomPadding.toFloat())
        val seed = (Math.random() * 3).toInt()
        when (seed) {
            0 -> rankWidth -= originsOffset
            1 -> rankWidth += originsOffset
            2 -> rankHeight -= originsOffset
        }
        val heartDrawableIndex: Int = (drawableList.size * Math.random()).toInt()
        val tempImageView = ImageView(context)
        tempImageView.setImageDrawable(drawableList[heartDrawableIndex])
        addView(tempImageView)
        val riseAlphaAnimator = ObjectAnimator.ofFloat(tempImageView, "alpha", 1.0f, 0.0f)
        riseAlphaAnimator.duration = riseDuration.toLong()
        val riseScaleXAnimator = ObjectAnimator.ofFloat(tempImageView, "scaleX", minScale, maxScale)
        riseScaleXAnimator.duration = riseDuration.toLong()
        val riseScaleYAnimator = ObjectAnimator.ofFloat(tempImageView, "scaleY", minScale, maxScale)
        riseScaleYAnimator.duration = riseDuration.toLong()
        val valueAnimator = getBesselAnimator(tempImageView, rankWidth, rankHeight)
        val animatorSet = AnimatorSet()
        animatorSet.play(valueAnimator).with(riseAlphaAnimator).with(riseScaleXAnimator)
            .with(riseScaleYAnimator)
        animatorSet.start()
    }

    private fun getBesselAnimator(
        imageView: ImageView,
        rankWidth: Int,
        rankHeight: Int
    ): ValueAnimator {
        val point0 = FloatArray(2)
        point0[0] = (rankWidth / 2).toFloat()
        point0[1] = rankHeight.toFloat()
        val point1 = FloatArray(2)
        point1[0] = (rankWidth * 0.10).toFloat() + (Math.random() * rankWidth * 0.8).toFloat()
        point1[1] = (rankHeight - Math.random() * rankHeight * 0.5).toFloat()
        val point2 = FloatArray(2)
        point2[0] = (Math.random() * rankWidth).toFloat()
        point2[1] = (Math.random() * (rankHeight - point1[1])).toFloat()
        val point3 = FloatArray(2)
        point3[0] = (Math.random() * rankWidth).toFloat()
        point3[1] = 0F
        val besselEvaluator = BesselEvaluator(point1, point2)
        val valueAnimator = ValueAnimator.ofObject(besselEvaluator, point0, point3)
        valueAnimator.duration = riseDuration.toLong()
        valueAnimator.addUpdateListener { animation ->
            var currentPosition = FloatArray(2)
            currentPosition = animation.animatedValue as FloatArray
            imageView.translationX = currentPosition[0]
            imageView.translationY = currentPosition[1]
        }
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                removeView(imageView)
                imageView.setImageDrawable(null)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        return valueAnimator
    }

    inner class BesselEvaluator(point1: FloatArray, point2: FloatArray) :
        TypeEvaluator<FloatArray> {
        private var point1 = FloatArray(2)
        private var point2 = FloatArray(2)
        override fun evaluate(fraction: Float, point0: FloatArray, point3: FloatArray): FloatArray {
            val currentPosition = FloatArray(2)
            currentPosition[0] =
                point0[0] * (1 - fraction) * (1 - fraction) * (1 - fraction) + point1[0] * 3 * fraction * (1 - fraction) * (1 - fraction) + point2[0] * 3 * (1 - fraction) * fraction * fraction + point3[0] * fraction * fraction * fraction
            currentPosition[1] =
                point0[1] * (1 - fraction) * (1 - fraction) * (1 - fraction) + point1[1] * 3 * fraction * (1 - fraction) * (1 - fraction) + point2[1] * 3 * (1 - fraction) * fraction * fraction + point3[1] * fraction * fraction * fraction
            return currentPosition
        }

        init {
            this.point1 = point1
            this.point2 = point2
        }
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}