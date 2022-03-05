package top.iqqcode.a02_pagerdemos;

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
 * @Author: iqqcode
 * @Date: 2021-03-31 15:54
 * @Description:
 */
public class BaseFragment extends Fragment {

    private Context mContext;
    private TextView mTextView;

    private String title;
    private String content;

    /**
     * 获得标题
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 获得内容
     * @return
     */
    public String getContent() {
        return content;
    }

    public BaseFragment(String title, String content) {
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
        mTextView.setTextColor(Color.WHITE);
        mTextView.setTextSize(25);
        mTextView.setGravity(Gravity.CENTER);
        return mTextView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 设置内容
        mTextView.setText(content);
    }
}
