package top.iqqcode.alivelike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import top.iqqcode.alivelike.databinding.ActivityMainBinding
import java.util.*

/**
 * 原文:
 * @link https://www.jianshu.com/p/7e4da38ffde6 activity
 * @constructor Create empty Main activity
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mLikeButton: Button

    private var mLastTime: Long = 0
    private var mCurTime: Long = 0
    private val DELAY = 500 //连续点击的临界点

    private var bubbleView: BubbleView? = null
    private var mClickCount = 0
    private var currLikeCount = 0
    private var likeCount: TextView? = null
    private var delayTimer: Timer? = null
    private var timeTask: TimerTask? = null

    private val mHandler: Handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            addLiveLick(mClickCount)
            delayTimer?.cancel()
            super.handleMessage(msg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bubbleView = binding.bubbleView
        bubbleView!!.setDefaultDrawableList()
        mLikeButton = binding.likeButton
        mLikeButton.setOnClickListener {
            mCurTime = System.currentTimeMillis()
            if (mCurTime - mLastTime < DELAY) {
                mClickCount++
            } else {
                mClickCount = 1
            }
            currLikeCount++
            delay()
            mLastTime = mCurTime
            bubbleView!!.startAnimation(bubbleView!!.width, bubbleView!!.height)
        }
    }

        private fun delay() {
            if (timeTask == null || delayTimer == null) {
                return
            }
            timeTask?.cancel()
            object : TimerTask() {
                override fun run() {
                    val message = Message()
                    mHandler.sendMessage(message)
                }
            }
            delayTimer = Timer()
            delayTimer?.schedule(timeTask, DELAY.toLong())
        }

        /**
         * 点赞请求网络
         */
        private fun addLiveLick(count: Int) {}
}