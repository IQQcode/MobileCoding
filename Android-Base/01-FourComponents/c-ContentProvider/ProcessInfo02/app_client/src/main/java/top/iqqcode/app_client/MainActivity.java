package top.iqqcode.app_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyResolver myResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myResolver = new MyResolver(this);
    }

    public void clickQuery(View v) {
        ArrayList<User> users = myResolver.query();
        Log.e("xxx", "clickQuery: " + users.toString());
    }

    public void clickInsert(View v) {
        User user = new User(1003, "王五");
        myResolver.insert(user);
    }
}