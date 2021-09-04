package top.iqqcode.tablayout01;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Author: jiazihui
 * @Date: 2021-08-17 23:53
 * @Description:
 */
public class TabFragment extends Fragment {

    private Context mContext;
    private TextView mTextView;
    private final String title, content;

    public TabFragment(String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTextView = new TextView(mContext);
        mTextView.setTextColor(Color.DKGRAY);
        mTextView.setTextSize(25);
        mTextView.setGravity(Gravity.CENTER);
        return mTextView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextView.setText(content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
