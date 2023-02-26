package top.iqqcode.textcontent.span;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import top.iqqcode.textcontent.R;
import top.iqqcode.textcontent.databinding.ActivityAtBinding;
import top.iqqcode.textcontent.util.UtilHelper;

public class AtActivity extends AppCompatActivity {

    private static ActivityAtBinding binding;
    private final List<int[]> mHighLightPositionList = new ArrayList<>();

    private final static String ORIGIN_TEXT = "@🔯防水逆专用☪️ 哈#(吐舌)@金裕贞就是我 哒哒";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAtBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        obtainShowAtInfoPosition(ORIGIN_TEXT);
        handleKeyWordColor();
    }

    private void obtainShowAtInfoPosition(String textMsg) {
        if (TextUtils.isEmpty(textMsg)) {
            return;
        }
        mHighLightPositionList.clear();
        mHighLightPositionList.add(new int[] {0, 10});
        mHighLightPositionList.add(new int[] {17,24});
    }

    /**
     * At@用户名高亮
     * @return
     */
    public void handleKeyWordColor() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(ORIGIN_TEXT);
        ForegroundColorSpan spanRed = new ForegroundColorSpan(Color.parseColor("#ff6b81"));
        for (int[] pos : mHighLightPositionList) {
            int startPos = pos[0];
            int endPos = pos[1];
            if (startPos < builder.length() && endPos < builder.length()) {
                builder.setSpan(spanRed, startPos, endPos, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
        binding.atTextView.setText(builder);
    }
}