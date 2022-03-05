package top.iqqcode.tablayoutattribute;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import top.iqqcode.tablayoutattribute.defaultuse.MainActivity;
import top.iqqcode.tablayoutattribute.setstyle.SetStyleActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void defaultUse(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void fieldValue(View view) {
        startActivity(new Intent(this, SetStyleActivity.class));
    }

}
