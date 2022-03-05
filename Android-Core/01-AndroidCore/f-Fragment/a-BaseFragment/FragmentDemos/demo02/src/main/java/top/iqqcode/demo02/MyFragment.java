package top.iqqcode.demo02;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-03-29 15:36
 * @Description:
 */
public class MyFragment extends Fragment {
    private String ans;
    private int pos;

    private int[] images = {R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04};

    public void setContext(String context) {
        this.ans = context;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.context_fragment, container, false);
        ImageView mImageView = view.findViewById(R.id.context_image);
        mImageView.setImageResource(images[pos]);
        TextView mTextView = view.findViewById(R.id.txt_content);
        mTextView.setText(ans);
        return view;
    }
}
