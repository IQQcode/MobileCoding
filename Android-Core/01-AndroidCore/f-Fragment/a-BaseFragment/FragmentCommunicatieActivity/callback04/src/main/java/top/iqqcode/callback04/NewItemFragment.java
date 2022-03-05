package top.iqqcode.callback04;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Author: iqqcode
 * @Date: 2021-03-27 08:36
 * @Description:
 */
public class NewItemFragment extends Fragment {

    // 声明一个接口，定义你要向Activity传值的方法
    public interface OnNewItemAddListener {
        void onNewItemAdd(String content);
    }

    // 声明一个接口引用变量
    private OnNewItemAddListener onNewItemAddListener;

    /**
     * 当该Fragment被添加到Activity中会回调，只会被调用一次
     *
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 要求该Fragment所附着的Activity必须完成这个方法实现
        try {
            onNewItemAddListener = (OnNewItemAddListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException(context + "Activity必须声明该接口");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_item_fragment, container, false);
        EditText mEditText = view.findViewById(R.id.et_input_list);
        // 绑定键盘事件
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 按下键盘
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 输入完成
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        String content_edit = mEditText.getText().toString();
                        onNewItemAddListener.onNewItemAdd(content_edit);
                        mEditText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }
}
