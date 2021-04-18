package top.iqqcode.viewbinding

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * @Author: iqqcode
 * @Date: 2021-04-11 22:30
 * @Description:
 */
class FragmentDemo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mTextView = TextView(activity)
        mTextView.text = "Fragment1"
        mTextView.setTextColor(Color.WHITE)
        mTextView.textSize = 25f
        mTextView.gravity = Gravity.CENTER
        mTextView.setTypeface(Typeface.SERIF, Typeface.BOLD)
        mTextView.setBackgroundColor(Color.parseColor("#70a1ff"))
        return mTextView
    }
}