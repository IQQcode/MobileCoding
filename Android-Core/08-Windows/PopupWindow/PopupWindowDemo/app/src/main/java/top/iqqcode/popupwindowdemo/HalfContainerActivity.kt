package top.iqqcode.popupwindowdemo

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.popupwindowdemo.databinding.ActivityHalfContainerBinding

class HalfContainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHalfContainerBinding

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalfContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rootView = binding.root
        binding.rootButton.setOnClickListener {
            initPopWindow(it)
        }
    }

    private fun initPopWindow(v: View) {
        val view: View = LayoutInflater.from(this).inflate(R.layout.item_popup_view, null, false)
        val mBtnRemove = view.findViewById(R.id.btn_remove) as Button
        val mBtnMark = view.findViewById(R.id.btn_mark) as Button
        //  1.构造一个PopupWindow，参数依次是加载的View，宽高
        val popWindow = PopupWindow(view,
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

        // 这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        // 代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        // PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.isTouchable = true
        popWindow.setTouchInterceptor(object : View.OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return false
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        })
        popWindow.setBackgroundDrawable(ColorDrawable(0x00000000)) //要为popWindow设置一个背景才有效


        // 设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 50, 0)

        // 设置popupWindow里的按钮的事件
        mBtnRemove.setOnClickListener {
            Toast.makeText(this@HalfContainerActivity,
                "你点击了嘻嘻~",
                Toast.LENGTH_SHORT).show()
            popWindow.dismiss()
        }
        mBtnMark.setOnClickListener {
            Toast.makeText(this@HalfContainerActivity, "你点击了呵呵~", Toast.LENGTH_SHORT).show()
            popWindow.dismiss()
        }
    }
}