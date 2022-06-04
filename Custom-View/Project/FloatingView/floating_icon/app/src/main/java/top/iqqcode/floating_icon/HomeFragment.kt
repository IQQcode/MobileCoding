package top.iqqcode.floating_icon

import android.animation.Animator
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.animation.ObjectAnimator
import android.widget.Toast
import android.view.View
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(), View.OnClickListener {
    private var floatingIconView: FloatingIconView? = null
    private var isRecycleViewScroll = false
    private var isTop = false
    private var isBottom = false
    private var isAnimatorEnd = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycle)
        floatingIconView = view.findViewById<View>(R.id.share_image) as FloatingIconView
        floatingIconView!!.setOnClickListener(this)

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = HomeAdapter()
        recyclerView.addOnScrollListener(MyScrollListener())
        return view
    }

    private inner class MyScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    isRecycleViewScroll = false
                    showShareImage()
                }
                RecyclerView.SCROLL_STATE_DRAGGING -> if (!isRecycleViewScroll && !isBottom && !isTop) {
                    isRecycleViewScroll = true
                    hideShareImage()
                }
                RecyclerView.SCROLL_STATE_SETTLING -> if (!isRecycleViewScroll && !isBottom && !isTop) {
                    isRecycleViewScroll = true
                    hideShareImage()
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val bottom = recyclerView.canScrollVertically(1)
            val top = recyclerView.canScrollVertically(-1)
            isBottom = !bottom
            isTop = !top
        }
    }

    private fun showShareImage() {
        val translationX = floatingIconView!!.translationX
        val animator = ObjectAnimator.ofFloat(floatingIconView, "translationX", 0f)
        animator.duration = 600
        if (!isAnimatorEnd) {
            animator.startDelay = 1200
        }
        animator.start()
    }

    private fun hideShareImage() {
        isAnimatorEnd = false
        val translationX = floatingIconView!!.translationX
        val animator = ObjectAnimator.ofFloat(
            floatingIconView, "translationX", FloatingIconView.Companion.dp2px(
                activity, 70f
            ).toFloat()
        )
        animator.duration = 600
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                isAnimatorEnd = true
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        animator.start()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.share_image) {
            Toast.makeText(activity, "点击了", Toast.LENGTH_SHORT).show()
        }
    }
}