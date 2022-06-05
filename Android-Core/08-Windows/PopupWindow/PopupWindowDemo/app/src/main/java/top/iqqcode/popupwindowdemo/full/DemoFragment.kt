package top.iqqcode.popupwindowdemo.full

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import top.iqqcode.popupwindowdemo.R

class DemoFragment : Fragment() {

    private var rootView: View? = null

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?,
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_demo, container, false)
        return rootView
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val textView = rootView!!.findViewById<TextView>(R.id.text_view)
        val text = if (arguments != null) requireArguments().getString("text") else null
        textView.text = text
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