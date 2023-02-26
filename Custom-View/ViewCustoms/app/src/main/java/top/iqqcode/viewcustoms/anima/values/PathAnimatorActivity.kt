package top.iqqcode.viewcustoms.anima.values

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import top.iqqcode.viewcustoms.R
import top.iqqcode.viewcustoms.base.FragmentExt
import top.iqqcode.viewcustoms.base.OptFragment
import top.iqqcode.viewcustoms.databinding.ActivityPointAnimatorBinding


/**
 * 属性动画基础使用
 *
 * @constructor Create empty Point animator activity
 */
class PathAnimatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPointAnimatorBinding
    private lateinit var mOptFragment: OptFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPointAnimatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiveOpt()
    }

    private fun receiveOpt() {
        mOptFragment = FragmentExt.createFragment(R.layout.fragment_path)
        FragmentExt.addFragment(
            this.supportFragmentManager,
            R.id.optFragmentContainer,
            mOptFragment
        )
    }

}
