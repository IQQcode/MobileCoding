package top.iqqcode.hitoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.*;

import top.iqqcode.hitoday.bean.HistoryBean;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView emptyTextView;
    private ListView listView;
    private ImageView bacKImageView;
    List<HistoryBean.ResultBean> resultBeanList;
    private HistoryAdapter adapter;
    HistoryBean historyBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        emptyTextView = (TextView) findViewById(R.id.history_tv);
        listView = (ListView) findViewById(R.id.history_lv);
        bacKImageView = (ImageView) findViewById(R.id.history_iv_back);
        bacKImageView.setOnClickListener(this);
        resultBeanList = new ArrayList<>();
        adapter = new HistoryAdapter(this, resultBeanList);
        listView.setAdapter(adapter);


        // 获取上一级的数据
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            historyBean = (HistoryBean) bundle.getSerializable("history");
            List<HistoryBean.ResultBean> list = historyBean.getResult();
            resultBeanList.addAll(list);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            // 如果出现异常无法显示，则让其显示空的Activity
            e.printStackTrace();
            emptyTextView.setVisibility(View.VISIBLE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HistoryActivity.this, HistoryDescActivity.class);
                HistoryBean.ResultBean resultBean = resultBeanList.get(position);
                String bean_id = resultBean.get_id();
                intent.putExtra("hisId", bean_id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.history_iv_back:
                finish();
                break;
        }
    }
}