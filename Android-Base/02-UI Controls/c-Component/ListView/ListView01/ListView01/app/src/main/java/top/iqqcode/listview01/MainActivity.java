package top.iqqcode.listview01;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2021/4/20
 * @Description:ListView上手使用
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Fruit> fruitList = new ArrayList<>();

    // 定义Data
    private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
            "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits(); // 初始化水果数据

        ListView mListView = findViewById(R.id.list_view);


        // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        MyAdapter adapter = new MyAdapter(this, R.layout.list_item_view, fruitList);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(this);

    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.mipmap.ic_launcher);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.mipmap.ic_launcher);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.mipmap.ic_launcher);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.mipmap.ic_launcher);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.mipmap.ic_launcher);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.mipmap.ic_launcher);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.mipmap.ic_launcher);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.mipmap.ic_launcher);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.mipmap.ic_launcher);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.mipmap.ic_launcher);
            fruitList.add(mango);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fruit fruit = fruitList.get(position);
        Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
    }
}