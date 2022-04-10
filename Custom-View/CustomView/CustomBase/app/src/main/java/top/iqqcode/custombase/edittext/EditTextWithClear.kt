package top.iqqcode.custombase.edittext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText
import androidx.core.content.ContextCompat
import top.iqqcode.custombase.R

/**
 * @Author: iqqcode
 * @Date: 2022-04-04 14:27
 * @Description:自定义带清除的EditText
 */
@SuppressLint("AppCompatCustomView")
class EditTextWithClear @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    EditText(context, attrs) {

    private lateinit var iconDrawable: Drawable


    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.EditTextWithClear, 0, 0).apply {
            try {
                val iconID = getResourceId(R.styleable.EditTextWithClear_clearIcon, 0)
                if (iconID != 0) {
                    iconDrawable = ContextCompat.getDrawable(context, iconID)!!
                }
            } finally {
                recycle()
            }
        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int,
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        toggleClearIcon()
    }

    private fun toggleClearIcon() {
        val iconShow = if (isFocused && text?.isNotEmpty() == true) iconDrawable else null
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, iconShow, null)
    }

    /**
     * 失去焦点时图标消失
     * @param focused Boolean
     * @param direction Int
     * @param previouslyFocusedRect Rect?
     */
    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        toggleClearIcon()
    }

    /**
     * 有warning是应为有些设备(手机模拟器)无触摸事件，但是可以点击
     * @param event MotionEvent
     * @return Boolean
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // let非空对象判断
        event?.let { e ->

            iconDrawable?.let {
                if (e.action == MotionEvent.ACTION_UP
                    && e.x < width + 20 // 触摸的横坐标小于整个控件(EditTextWithClear)的宽度
                    && e.x > width - it.intrinsicWidth - 20 // 触摸的横坐标大于❌icon的宽度
                    && e.y > height / 2 - it.intrinsicHeight / 2 - 20 // 画图(+-20提高容错度)
                    && e.y < height / 2 + it.intrinsicHeight / 2 + 20
                ) {
                    text?.clear()
                }
            }
        }
        return super.onTouchEvent(event)
    }
}