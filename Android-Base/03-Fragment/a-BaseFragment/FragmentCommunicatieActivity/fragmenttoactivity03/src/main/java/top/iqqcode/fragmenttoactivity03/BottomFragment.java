package top.iqqcode.fragmenttoactivity03;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-03-26 20:13
 * @Description:
 */
public class BottomFragment extends Fragment {

    private Button mButton;

    private String LocalVariable = "获取Fragment局部变量";

    public String getLocalVariable() {
        return LocalVariable;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        // Fragment中获取UI控件
        mButton = view.findViewById(R.id.btn_judge);
        return view;
    }

    /**
     * Fragment和Activity均创建好调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CheckBox checkBox = getActivity().findViewById(R.id.cb_is_study);
        // 获得Activity的成员变量
        String res = ((MainActivity) getActivity()).getVariable();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox != null && checkBox.isChecked()) {
                    Toast.makeText(getActivity(), "CheckBox选中" + res, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "CheckBox未选中", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}