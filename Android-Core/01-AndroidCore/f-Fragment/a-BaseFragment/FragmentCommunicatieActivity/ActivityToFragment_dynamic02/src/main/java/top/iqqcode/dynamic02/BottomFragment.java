package top.iqqcode.dynamic02;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-03-26 17:07
 * @Description:
 */
public class BottomFragment extends Fragment {

    private String LocalVariable = "获取Fragment局部变量";

    public String getLocalVariable() {
        return LocalVariable;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        return view;
    }
}
