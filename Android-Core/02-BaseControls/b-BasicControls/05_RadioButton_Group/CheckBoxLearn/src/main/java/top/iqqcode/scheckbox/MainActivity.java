package top.iqqcode.scheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * @Author: iqqcode
 * @Date: 2021/3/13
 * @Description:CheckBox
 */
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox mCheckBox01, mCheckBox02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCheckBox01 = findViewById(R.id.checkbox01);
        mCheckBox02 = findViewById(R.id.checkbox02);

        mCheckBox01.setOnCheckedChangeListener(this);
        mCheckBox02.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == mCheckBox01) {
            Toast.makeText(MainActivity.this, isChecked ? "CheckBox01选中" : "CheckBox01未选中", Toast.LENGTH_SHORT).show();
        } else if (buttonView == mCheckBox02) {
            Toast.makeText(MainActivity.this, isChecked ? "CheckBox02选中" : "CheckBox02未选中", Toast.LENGTH_SHORT).show();
        }
    }
}