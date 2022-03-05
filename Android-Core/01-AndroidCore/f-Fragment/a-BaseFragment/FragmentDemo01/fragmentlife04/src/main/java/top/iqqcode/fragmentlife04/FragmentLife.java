package top.iqqcode.fragmentlife04;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * @Author: iqqcode
 * @Date: 2021-03-24 12:42
 * @Description:
 */
public class FragmentLife extends Fragment {

    private static final String TAG = "TAG";

    /**
     * 获得父Activity的引用
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e(TAG, "Fragment01 == onAttach ---> 01");
    }

    /**
     * 创建Fragment并初始化
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Fragment01 == onCreate ---> 02");
    }

    /**
     * Fragment需要和UI绑定时，需要返回view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment01 == onCreateView ---> 03");
        // 创建视图对象，设置数据并返回
        TextView mTextView = new TextView(getActivity());
        mTextView.setText("Fragment1");
        mTextView.setTextColor(Color.WHITE);
        mTextView.setTextSize(25);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTypeface(Typeface.SERIF,Typeface.BOLD);
        mTextView.setBackgroundColor(Color.parseColor("#70a1ff"));
        return mTextView;
    }

    /**
     * 当Fragment和所属的Activity均创建时调用(使用二者里面的UI需要在此之后执行)
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "Fragment01 == onActivityCreated ---> 04");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "Fragment01 == onStart ---> 05");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "Fragment01 == onResume ---> 06");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "Fragment01 == onPause ---> 07");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "Fragment01 == onStop ---> 08");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "Fragment01 == onDestroyView ---> 09");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Fragment01 == onDestroy ---> 10");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "Fragment01 == onDetach ---> 11");
    }
}
