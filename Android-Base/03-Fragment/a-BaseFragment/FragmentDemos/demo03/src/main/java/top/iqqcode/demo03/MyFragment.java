package top.iqqcode.demo03;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-03-29 18:08
 * @Description:
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn_one = view.findViewById(R.id.btn_one);
        btn_two = view.findViewById(R.id.btn_two);
        btn_three = view.findViewById(R.id.btn_three);
        btn_four = view.findViewById(R.id.btn_four);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                TextView tab_index_num = getActivity().findViewById(R.id.tab_menu_index_num);
                tab_index_num.setText("11");
                tab_index_num.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_two:
                TextView tab_message_num = getActivity().findViewById(R.id.tab_menu_message_num);
                tab_message_num.setText("99+");
                tab_message_num.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_three:
                TextView tab_fun_num = getActivity().findViewById(R.id.tab_menu_fun_num);
                tab_fun_num.setText("15");
                tab_fun_num.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_four:
                ImageView tab_setting_num = getActivity().findViewById(R.id.tab_menu_setting_partner);
                tab_setting_num.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
