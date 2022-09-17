package top.iqqcode.viewevent.drag

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.viewevent.R
import top.iqqcode.viewevent.databinding.ActivityTouchMoveBinding

/**
 * 自定义任意拖拽的View
 * @constructor Create empty Touch move activity
 */
class TouchMoveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTouchMoveBinding

    private lateinit var mCustomDragView: DragView
    private lateinit var mMyView: MyView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 去除标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityTouchMoveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mCustomDragView = findViewById(R.id.mCustomDragView)
        mCustomDragView.setOnClickListener {
            if (!mCustomDragView.isDrag) {
                Toast.makeText(this@TouchMoveActivity, "响应点击", Toast.LENGTH_SHORT).show();
            }
        }

        mMyView = findViewById(R.id.mCustomMyView)
    }
}