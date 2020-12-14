package com.iqqcode.demoorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.iqqcode.demoorder.model.Food;
import com.iqqcode.demoorder.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private RadioGroup mSexRadioGroup;
    private CheckBox mHotCheckBox, mFishCheckBox, mSourCheckBox;
    private SeekBar mSeekBar;
    private Button mSearchButton;
    private ImageView mFoodImageView;
    private ToggleButton mToggleButton;
    private List<Food> mFoods;
    private Person mPerson;
    private List<Food> mFoodResult;
    private boolean mIsFish;
    private boolean mIsSour;
    private boolean mIsHot;
    private int mPrice;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 初始化控件
        findViews();
        // 2. 初始化数据
        initData();
        // 3. 为控件添加监听器，实现基本功能
        setListeners();
        // 4. 自测
    }

    private void findViews() {
        mNameEditText = findViewById(R.id.nameEditText);
        mSexRadioGroup = findViewById(R.id.sexRadioGroup);
        mHotCheckBox = findViewById(R.id.hotCheckBox);
        mFishCheckBox = findViewById(R.id.fishCheckBox);
        mSourCheckBox = findViewById(R.id.sourCheckBox);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setProgress(30);
        mSearchButton = findViewById(R.id.searchButton);
        mToggleButton = findViewById(R.id.showToggleButton);
        mToggleButton.setChecked(true);
        mFoodImageView = findViewById(R.id.foodImageView);
    }

    private void initData() {
        // new 出来一个空的食物 list
        mFoods = new ArrayList<>();

        // 初始化添加所有的数据
        mFoods.add(new Food("麻辣香锅", 55, R.drawable.malaxiangguo, true, false, false));
        mFoods.add(new Food("水煮鱼", 48, R.drawable.shuizhuyu, true, true, false));
        mFoods.add(new Food("麻辣火锅", 80, R.drawable.malahuoguo, true, true, false));
        mFoods.add(new Food("清蒸鲈鱼", 68, R.drawable.qingzhengluyu, false, true, false));
        mFoods.add(new Food("桂林米粉", 15, R.drawable.guilin, false, false, false));
        mFoods.add(new Food("上汤娃娃菜", 28, R.drawable.wawacai, false, false, false));
        mFoods.add(new Food("红烧肉", 60, R.drawable.hongshaorou, false, false, false));
        mFoods.add(new Food("木须肉", 40, R.drawable.muxurou, false, false, false));
        mFoods.add(new Food("酸菜牛肉面", 35, R.drawable.suncainiuroumian, false, false, true));
        mFoods.add(new Food("西芹炒百合", 38, R.drawable.xiqin, false, false, false));
        mFoods.add(new Food("酸辣汤", 40, R.drawable.suanlatang, true, false, true));

        mPerson = new Person();

        mFoodResult = new ArrayList<>();
    }

    private void setListeners() {
        // 设置单选框listener
        mSexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.maleRadioButton:
                        mPerson.setSex("男");
                        break;
                    case R.id.femaleRadioButton:
                        mPerson.setSex("女");
                        break;
                }
            }
        });

        // 设置复选框listener
        mFishCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsFish = isChecked;
            }
        });

        mSourCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsSour = isChecked;
            }
        });
        mHotCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsHot = isChecked;
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPrice = seekBar.getProgress();
                Toast.makeText(MainActivity.this, "价格：" + mPrice, Toast.LENGTH_SHORT).show();
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mToggleButton.isChecked()) {
                    mCurrentIndex++;
                    if (mCurrentIndex < mFoodResult.size()) {
                        mFoodImageView.setImageResource(mFoodResult.get(mCurrentIndex).getPic());
                    } else {
                        Toast.makeText(MainActivity.this, "没有啦", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 显示信息: 菜品名称
                    if (mCurrentIndex < mFoodResult.size()) {
                        Toast.makeText(MainActivity.this, "菜名： " + mFoodResult.get(mCurrentIndex).getName(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "没有啦", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // 查找菜品
    private void search() {
        // 先初始化
        if (mFoodResult == null) {
            mFoodResult = new ArrayList<>();
        }
        // 结果列表每次都清空
        mFoodResult.clear();

        // 当前显示的是结果集中的哪个菜
        mCurrentIndex = 0;

        // 遍历所有菜
        for (int i = 0; i < mFoods.size(); i++) {
            Food food = mFoods.get(i);
            if (food != null) {
                if (food.getPrice() <= mPrice && (food.isFish() == mIsFish || food.isHot() == mIsHot || food.isSour() == mIsSour)) {
                    // 如果符合条件的，加入列表
                    mFoodResult.add(food);
                }
            }
        }

        if (mCurrentIndex < mFoodResult.size()) {
            mFoodImageView.setImageResource(mFoodResult.get(mCurrentIndex).getPic());
        }
    }

}