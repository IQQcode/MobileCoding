package top.iqqcode.page02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Author: iqqcode
 * @Date: 2021-03-30 17:27
 * @Description:
 */
public class BaseFragment extends Fragment {

    public static final String TITLE = "title";
    private String pos = null;

    /**
     * Fragment向Activity传递参数(不建议使用构造方法)
     * @param content
     * @return
     */
    public static BaseFragment newInstance(String content) {
        BaseFragment fragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pos = String.valueOf(getArguments().getString(TITLE));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView textView = view.findViewById(R.id.fragment_text_view);
        textView.setText(pos);
        return view;
    }
}