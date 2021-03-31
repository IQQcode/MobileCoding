package top.iqqcode.pager03;

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
 * @Date: 2021-03-31 10:34
 * @Description:
 */
public class BaseFragment extends Fragment {

    private static String TITLE = "title";
    private String ans = null;

    public static BaseFragment newInstance(String title) {
        BaseFragment fragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ans = getArguments().getString(TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup parent;
        ViewGroup root;
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        TextView textView = view.findViewById(R.id.fragment_text_view);
        textView.setText(ans);
        return view;
    }
}
