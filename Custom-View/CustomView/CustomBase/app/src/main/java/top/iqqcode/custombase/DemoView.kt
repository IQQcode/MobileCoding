package top.iqqcode.custombase

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * @Author: iqqcode
 * @Date: 2022-04-03 11:52
 * @Description:
 */
class DemoView : View {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }
}