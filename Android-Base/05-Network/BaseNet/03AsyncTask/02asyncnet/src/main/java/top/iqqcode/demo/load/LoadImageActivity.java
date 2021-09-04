package top.iqqcode.demo.load;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import top.iqqcode.demo.R;

public class LoadImageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Bitmap> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        @SuppressLint("WrongViewCast")
        final TaskImageView myImageView = findViewById(R.id.image_view);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接把网络的图片路径写上就可以显示网络的图片了
                String url = "https://images.unsplash.com/photo-1548400636-b8d3a313d209?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80";
                //设置成true才会启动缓存，默认是false
                myImageView.isUseCache = true;
                myImageView.setImageURL(url);
            }
        });
    }
}