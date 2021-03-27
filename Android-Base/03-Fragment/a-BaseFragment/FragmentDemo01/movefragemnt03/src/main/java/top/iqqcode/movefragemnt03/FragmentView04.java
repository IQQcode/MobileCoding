package top.iqqcode.movefragemnt03;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-03-22 18:37
 * @Description:
 */
public class FragmentView04 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加载视图View对象并返回
        View view = inflater.inflate(R.layout.fragment_view04,container, false);
        // 创建视图对象，设置数据并返回
        TextView mTextView = view.findViewById(R.id.tv_context_04);
        mTextView.setText("Fragment4");
        return view;
    }
}
