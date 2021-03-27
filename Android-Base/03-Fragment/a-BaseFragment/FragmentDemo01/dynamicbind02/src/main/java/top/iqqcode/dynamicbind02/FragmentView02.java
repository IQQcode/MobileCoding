package top.iqqcode.dynamicbind02;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

/**
 * @Author: iqqcode
 * @Date: 2021-03-22 18:37
 * @Description:
 */
public class FragmentView02 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加载视图View对象并返回

        // 创建视图对象，设置数据并返回
        TextView mTextView = new TextView(getActivity());
        mTextView.setText("Fragment2");
        mTextView.setTextColor(Color.WHITE);
        mTextView.setTextSize(25);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setBackgroundColor(Color.parseColor("#ff7f50"));
        mTextView.setTypeface(Typeface.SERIF, Typeface.BOLD);
        return mTextView;
    }
}
