package top.iqqcode.fragmentdemos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @Author: iqqcode
 * @Date: 2021-03-29 14:03
 * @Description:
 */
public class MyFragment extends Fragment {

    private int pos;
    private String content;

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private int[] images = {R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.context_fragment, container, false);
        ImageView mImageView = view.findViewById(R.id.context_image);
        mImageView.setImageResource(images[pos]);
        TextView mTextView = view.findViewById(R.id.txt_content);
        mTextView.setText(content);
        return view;
    }
}
