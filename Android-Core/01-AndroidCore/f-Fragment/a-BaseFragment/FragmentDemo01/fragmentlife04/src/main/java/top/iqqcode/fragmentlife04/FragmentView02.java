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
 * @Date: 2021-03-22 18:37
 * @Description:
 */
public class FragmentView02 extends Fragment {

    private static final String TAG = "TAG";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e(TAG, "Fragment02 == onAttach ---> 01");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Fragment02 == onCreate ---> 02");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment02 == onCreateView ---> 03");


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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "Fragment02 == onActivityCreated ---> 04");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "Fragment02 == onStart ---> 05");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "Fragment02 == onResume ---> 06");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "Fragment02 == onPause ---> 07");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "Fragment02 == onStop ---> 08");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "Fragment02 == onDestroyView ---> 09");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Fragment02 == onDestroy ---> 10");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "Fragment02 == onDetach ---> 11");
    }
}
