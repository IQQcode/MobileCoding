package top.iqqcode.textcontent.span

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.textcontent.R
import top.iqqcode.textcontent.databinding.ActivitySpanBinding
import top.iqqcode.textcontent.util.UtilHelper

/**
 * @author iqqcode
 * <a><a href="https://juejin.cn/post/6844903966761811981">SpannableStringBuilder 的使用</a>
 */
class SpanActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpanBinding
    private val textStr = "君不见黄河之水天上来，奔流到海不复回"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textColor()
        textBackGroundColor()
        textSize()
        textStyle()
        textStrike()
        textUnderLine()
        textWithIcon()
        textClickable()
        textStyleDemo()

        foregroundColorSpan()
    }

    /**
     * 字体颜色
     */
    private fun textColor() {
        val str01 = "君不见黄河之水天上来 "
        val str02 = "奔流到海不复回"
        val spanBuilder = SpannableStringBuilder().also {
            it.append(str01)
            it.append(str02)
            it.setSpan(
                ForegroundColorSpan(Color.parseColor("#b1f8c1")),
                0,
                10,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.spanTextView01.text = spanBuilder
    }

    /**
     * 字体背景色
     */
    private fun textBackGroundColor() {
        val spanBuilder = SpannableStringBuilder().also {
            it.append(textStr)
            it.setSpan(
                BackgroundColorSpan(Color.parseColor("#f8b1c4")),
                6,
                14,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.spanTextView02.text = spanBuilder
    }

    /**
     * 字体大小
     */
    private fun textSize() {
        val spanBuilder = SpannableStringBuilder().also {
            it.append(textStr)
            it.setSpan(
                AbsoluteSizeSpan(20),
                0,
                9,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.spanTextView03.text = spanBuilder
    }

    /**
     * 字体样式
     */
    private fun textStyle() {
        val spanBuilder = SpannableStringBuilder().also {
            it.append(textStr)
            // setSpan可多次使用
            val styleSpan = StyleSpan(Typeface.BOLD) //粗体
            it.setSpan(styleSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

            val styleSpan2 = StyleSpan(Typeface.ITALIC) // 斜体
            it.setSpan(styleSpan2, 3, 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

            val styleSpan3 = StyleSpan(Typeface.BOLD_ITALIC) // 粗斜体
            it.setSpan(styleSpan3, 6, 9, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        binding.spanTextView04.text = spanBuilder
    }

    /**
     * 字体删除线
     */
    private fun textStrike() {
        val spanBuilder = SpannableStringBuilder().also {
            it.append(textStr)
            it.setSpan(
                StrikethroughSpan(),
                0,
                9,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.spanTextView05.text = spanBuilder
    }

    /**
     * 字体下划线
     */
    private fun textUnderLine() {
        val spanBuilder = SpannableStringBuilder().also {
            it.append(textStr)
            it.setSpan(
                UnderlineSpan(),
                0,
                9,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.spanTextView06.text = spanBuilder
    }

    /**
     * 字体插入图标（图标占去该字符位置）
     */
    private fun textWithIcon() {
        val spanBuilder = SpannableStringBuilder().also {
            it.append(textStr)
            val imageSpan = getDrawable()?.let { it1 -> ImageSpan(it1) }
            it.setSpan(imageSpan, 12, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        binding.spanTextView07.text = spanBuilder
    }

    /**
     * 字体支持点击事件
     */
    private fun textClickable() {
        val spanBuilder = SpannableStringBuilder().also {
            it.append("君不见黄河之水天上来")
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    Toast.makeText(this@SpanActivity, "点击字体超链接", Toast.LENGTH_SHORT).show()
                }
            }
            it.setSpan(clickableSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        binding.spanTextView08.text = spanBuilder
        binding.spanTextView08.movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * Text style demo
     * 综合Demo
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun textStyleDemo() {
        val builder = SpannableStringBuilder().also {
            it.append(textStr)
            it.setSpan(ForegroundColorSpan(Color.BLUE), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(BackgroundColorSpan(Color.RED), 1, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(object : ClickableSpan() {

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    // ds.color = Color.parseColor("#FF151515");//设置颜色
                    // ds.isUnderlineText = false;//去掉下划线
                }

                override fun onClick(widget: View) {
                    Toast.makeText(this@SpanActivity, "听风吹雨", Toast.LENGTH_SHORT).show()
                    UtilHelper.dealLink("http://www.baidu.com", this@SpanActivity)
                }
            }, 3, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(StrikethroughSpan(), 8, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(UnderlineSpan(), 9, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(AbsoluteSizeSpan(50), 10, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(StyleSpan(Typeface.BOLD), 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val imageSpan = getDrawable()?.let { it1 -> ImageSpan(it1) }
            it.setSpan(imageSpan, 12, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        binding.spanTextViewDemo.text = builder
        binding.spanTextViewDemo.movementMethod = LinkMovementMethod.getInstance()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getDrawable(): Drawable? {
        val drawable = this.getDrawable(R.drawable.happy_emjio)
        val displayMetrics = DisplayMetrics();
        this.windowManager.defaultDisplay.getMetrics(displayMetrics);
        val size = 90 * displayMetrics.heightPixels / 2560;
        drawable?.setBounds(0, 0, size, size);
        return drawable
    }

    private fun foregroundColorSpan() {

        /**
         * Spanned中flags的标记，是在SpannableStringBuilder中使用的，在SpannableString中没有作用。
         * 【使用场景】
         * 使用了SpannableStringBuilder.insert(int,CharSeque)方法后，对于insert后的字符串是否进行扩展特性的标记，
         * 此标记作用的场景仅仅是insert的位置恰好处于start 或者 end两个端点的临界位置；
         * 即用flags标记这个临界点跟随哪个。
         */

        val textView01 = TextView(this)
        var spanBuilder = SpannableStringBuilder("手续费84.00元")
        val span1 = ForegroundColorSpan(Color.RED)
        spanBuilder.setSpan(span1, 3, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        textView01.text = spanBuilder
        binding.spanContainer.addView(textView01)

        // 插入的9扩展了特性，而插入的5未扩展特性
        spanBuilder.insert(3, "9").insert(9, "5")
        val textView02 = TextView(this)
        textView02.text = spanBuilder
        binding.spanContainer.addView(textView02)

        // 插入的9未扩展特性，而插入的5扩展了特性
        spanBuilder.insert(3, "9").insert(9, "5")
        val textView03 = TextView(this)
        textView03.text = spanBuilder
        binding.spanContainer.addView(textView03)

        // 移除指定span
        spanBuilder.removeSpan(span1)
        val textView04 = TextView(this)
        textView04.text = spanBuilder
        binding.spanContainer.addView(textView04)

        // 恢复SpannableStringBuilder
        spanBuilder = SpannableStringBuilder("手续费84.00元")
        spanBuilder.setSpan(span1, 3, 8, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        // 验证同样的情况，只要执行insert系列方法，，即使flags改变效果也不变
        val textView05 = TextView(this)
        textView05.text = spanBuilder
        binding.spanContainer.addView(textView05)
    }
}