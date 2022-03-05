package top.iqqcode.moredialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class DialogStyleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_style);
        TextView mTextView = findViewById(R.id.activitys_message);
        mTextView.setText("你确定要退出吗，亲亲🎉");
    }
}