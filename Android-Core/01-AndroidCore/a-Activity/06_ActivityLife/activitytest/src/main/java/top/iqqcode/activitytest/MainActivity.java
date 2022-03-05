package top.iqqcode.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @Author: iqqcode
 * @Date: 2021/3/17
 * @Description:
 * 当一个activity进入onStop()时，如何得知时由于用户点击了主页键，还是由于进入了另一个本程序的activity？
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "[>> MainActivity] ==> onCreate -- count=" + count);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast toast;
        Log.e(TAG, "[>> MainActivity] ==> onStop -- count=" + count);
        if (count == 0){
            toast = Toast.makeText(getApplicationContext(), "程序进入后台", Toast.LENGTH_SHORT);
        }else {
            toast = Toast.makeText(getApplicationContext(), "程序进入第二个活动", Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "[>> MainActivity] ==> onClick -- count=" + count);
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}