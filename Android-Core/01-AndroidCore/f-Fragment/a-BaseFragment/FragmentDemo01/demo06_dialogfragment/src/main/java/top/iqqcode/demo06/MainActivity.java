package top.iqqcode.demo06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Author: iqqcode
 * @Date: 2021/3/25
 * @Description:系统内置的三个Fragment
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup mRadioGroup;
    private Button mButton;

    // Dialog的类型
    public static final int DIALOG_TYPE_ALERT = 1;
    public static final int DIALOG_TYPE_DATA = 2;
    public static final int DIALOG_TYPE_TIME = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVIew();
    }

    private void initVIew() {
        mRadioGroup = findViewById(R.id.rad_dialog_type);
        mButton = findViewById(R.id.btn_create_dialog);
        mRadioGroup.setOnClickListener(this);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = mRadioGroup.getCheckedRadioButtonId();
        BaseDialogFragment baseFragment = null;
        switch (id) {
            case R.id.rad_dialog_alert:
                Toast.makeText(this, "Alert", Toast.LENGTH_SHORT).show();
                baseFragment = BaseDialogFragment.getInstance(DIALOG_TYPE_ALERT);
                break;
            case R.id.rad_dialog_date:
                Toast.makeText(this, "Date", Toast.LENGTH_SHORT).show();
                baseFragment = BaseDialogFragment.getInstance(DIALOG_TYPE_DATA);
                break;
            case R.id.rad_dialog_time:
                Toast.makeText(this, "Time", Toast.LENGTH_SHORT).show();
                baseFragment = BaseDialogFragment.getInstance(DIALOG_TYPE_TIME);
                break;
            default:
                break;
        }
        if (baseFragment != null) {
            baseFragment.show(getFragmentManager(), "Info");
        }
    }
}