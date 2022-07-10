package top.iqqcode.flexibledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.iqqcode.flexibledemo.R
import top.iqqcode.flexibledemo.TranslationActivity
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import android.content.res.ColorStateList
import android.view.View
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import top.iqqcode.flexibledemo.databinding.ActivityTranslationBinding

class TranslationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTranslationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActivity(savedInstanceState)
    }

    private fun initActivity(savedInstanceState: Bundle?) {
        binding.exit.setOnClickListener { view: View? -> finish() }
        val shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, Utils.dip2px(this, 20f).toFloat())
            .setTopRightCorner(CornerFamily.ROUNDED,
                Utils.dip2px(this, 20f).toFloat()) //.setRightEdge(bottomAppBarTopEdgeTreatment)
            .build()
        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
        shapeDrawable.fillColor = ColorStateList.valueOf(-0x1)

        binding.button1.setOnClickListener { view: View? ->
            TransitionManager.beginDelayedTransition(
                binding.root, AutoTransition())
            binding.text.visibility = View.GONE
        }
        binding.button2.setOnClickListener { view: View? ->
            TransitionManager.beginDelayedTransition(
                binding.root, AutoTransition())
            binding.text.visibility = View.VISIBLE
        }
        binding.button3.setOnClickListener { view: View? ->
            TransitionManager.beginDelayedTransition(
                binding.root, AutoTransition())
            binding.text.text = "内容"
        }
    }
}