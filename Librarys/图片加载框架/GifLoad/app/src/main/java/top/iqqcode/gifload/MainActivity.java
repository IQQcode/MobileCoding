package top.iqqcode.gifload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import top.iqqcode.gifload.databinding.ActivityMainBinding;
import top.iqqcode.gifload.virtual.CornerViewActivity;
import top.iqqcode.gifload.virtual.RoundDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonGlide01.setOnClickListener(this);
        binding.buttonMatrix.setOnClickListener(this);
        binding.buttonCorner.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonGlide01) {
            startActivity(new Intent(MainActivity.this, GlideLoadActivity.class));
        } else if (view.getId() == R.id.buttonMatrix) {
            startActivity(new Intent(MainActivity.this, CornerViewActivity.class));
        } else if (view.getId() == R.id.buttonCorner) {
            startActivity(new Intent(MainActivity.this, RoundDemoActivity.class));
        }
    }
}