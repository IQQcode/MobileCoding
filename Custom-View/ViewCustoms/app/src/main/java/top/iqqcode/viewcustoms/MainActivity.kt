package top.iqqcode.viewcustoms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import top.iqqcode.viewcustoms.anima.alpha.NaturalAlphaActivity
import top.iqqcode.viewcustoms.databinding.ActivityMainBinding
import top.iqqcode.viewcustoms.edittext.CustomTextActivity
import top.iqqcode.viewcustoms.anima.rain.FallingActivity
import top.iqqcode.viewcustoms.anima.values.PathAnimatorActivity
import top.iqqcode.viewcustoms.expend.entrance.FrsFloatEntranceActivity
import top.iqqcode.viewcustoms.expend.hotspot.ExpendBaseDemoActivity
import top.iqqcode.viewcustoms.expend.hotspot.ExpendClickActivity
import top.iqqcode.viewcustoms.expend.spread.SpreadActivity
import top.iqqcode.viewcustoms.flipper.CarouselDemoActivity
import top.iqqcode.viewcustoms.floats.FloatExpandActivity
import top.iqqcode.viewcustoms.floats.IMGroupEntryActivity
import top.iqqcode.viewcustoms.icon.ExpendBoxActivity
import top.iqqcode.viewcustoms.label.EmotionLabelActivity
import top.iqqcode.viewcustoms.print.PrintActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClick()
    }

    private fun initClick() {
        binding.customEditButton.setOnClickListener(this)
        binding.printButton.setOnClickListener(this)
        binding.expendButton.setOnClickListener(this)
        binding.groupEntryButton.setOnClickListener(this)
        binding.floatExpendButton.setOnClickListener(this)
        binding.floatExpendBaseDemo.setOnClickListener(this)
        binding.frsFloatExpendDemo.setOnClickListener(this)
        binding.carouselFloat.setOnClickListener(this)
        binding.naturalAlphaDemo.setOnClickListener(this)
        binding.iconSizeExpend.setOnClickListener(this)
        binding.emotionLabel.setOnClickListener(this)
        binding.eggFalling.setOnClickListener(this)
        binding.pathAnimator.setOnClickListener(this)
        binding.spreadButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.customEditButton -> startActivity(
                Intent(
                    this@MainActivity,
                    CustomTextActivity::class.java
                ).setClassName(
                    this@MainActivity,
                    "top.iqqcode.viewcustoms.edittext.CustomTextActivity"
                )
            )

            R.id.printButton -> startActivity(Intent(this@MainActivity, PrintActivity::class.java))
            R.id.expendButton -> startActivity(
                Intent(
                    this@MainActivity,
                    ExpendClickActivity::class.java
                )
            )

            R.id.groupEntryButton -> startActivity(
                Intent(
                    this@MainActivity,
                    IMGroupEntryActivity::class.java
                )
            )

            R.id.floatExpendButton -> startActivity(
                Intent(
                    this@MainActivity,
                    FloatExpandActivity::class.java
                )
            )

            R.id.floatExpendBaseDemo -> startActivity(
                Intent(
                    this@MainActivity,
                    ExpendBaseDemoActivity::class.java
                )
            )

            R.id.frsFloatExpendDemo -> startActivity(
                Intent(
                    this@MainActivity,
                    FrsFloatEntranceActivity::class.java
                )
            )

            R.id.carouselFloat -> startActivity(
                Intent(
                    this@MainActivity,
                    CarouselDemoActivity::class.java
                )
            )

            R.id.naturalAlphaDemo -> startActivity(
                Intent(
                    this@MainActivity,
                    NaturalAlphaActivity::class.java
                )
            )

            R.id.iconSizeExpend -> startActivity(
                Intent(
                    this@MainActivity,
                    ExpendBoxActivity::class.java
                )
            )

            R.id.emotionLabel -> startActivity(
                Intent(
                    this@MainActivity,
                    EmotionLabelActivity::class.java
                )
            )

            R.id.eggFalling -> {
                // startActivity(Intent(this@MainActivity, FallingActivity::class.java))
                // 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                ARouter.getInstance().build("/animations/ModuleAnimationMainActivity").navigation()
            }

            R.id.pathAnimator -> startActivity(
                Intent(
                    this@MainActivity,
                    PathAnimatorActivity::class.java
                )
            )

            R.id.spreadButton -> startActivity(
                Intent(
                    this@MainActivity,
                    SpreadActivity::class.java
                )
            )
        }
    }
}