package top.iqqcode.adapterandholder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @Author: iqqcode
 * @Date: 2021/4/28
 * @Description:ArrayAdapter
 */
public class MainActivity extends AppCompatActivity {

    String[] data = {"TextView", "EditText", "Button", "ImageButton", "RadioButton", "ToggleButton",
            "ImageView", "ProgressBar", "SeekBar", "RatingBar", "ScrollView", "Adapter", "TextView", "EditText", "Button", "ImageButton", "RadioButton", "ToggleButton",
            "ImageView", "ProgressBar", "SeekBar", "RatingBar", "ScrollView", "Adapter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mListView = findViewById(R.id.list_view);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.item_view, R.id.item_text, data);
        mListView.setAdapter(adapter);
    }
}