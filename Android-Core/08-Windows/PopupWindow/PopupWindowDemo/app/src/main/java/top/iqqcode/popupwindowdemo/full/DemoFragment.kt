package top.iqqcode.popupwindowdemo.full

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import top.iqqcode.popupwindowdemo.R
import top.iqqcode.popupwindowdemo.alive.BombView
import top.iqqcode.popupwindowdemo.databinding.FragmentDemoBinding

class DemoFragment : Fragment() {

    private lateinit var binding: FragmentDemoBinding

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val textView = binding.textView
        val text = if (arguments != null) requireArguments().getString("text") else null
        textView.text = text

        val mLottieView = binding.animationView
        // 设置动画JSON文件
        mLottieView.setAnimation(R.raw.designer)
        // 设置循环播放
        mLottieView.repeatCount = -1
        // 播放动画
        mLottieView.playAnimation()
    }

    companion object {
        fun getNewInstance(text: String?): DemoFragment {
            val args = Bundle()
            args.putString("text", text)
            val fragment = DemoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}