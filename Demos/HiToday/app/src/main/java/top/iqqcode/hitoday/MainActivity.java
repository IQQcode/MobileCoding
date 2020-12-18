package top.iqqcode.hitoday;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

import top.iqqcode.hitoday.base.BaseActivity;
import top.iqqcode.hitoday.base.ContentURL;
import top.iqqcode.hitoday.bean.HistoryBean;
import top.iqqcode.hitoday.bean.LunarBean;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ListView mainListView;
    private ImageButton imageButton;
    List<HistoryBean.ResultBean> resultList;
    private HistoryAdapter adapter;
    private Calendar calendar;
    private Date date;
    HistoryBean historyBean;

    private static int HISTORY_TODAY_DETAILS = 5;

    // 声明头布局当中的TextView
    TextView lunarTv, dayTv, weekTv, solarTv;
    TextView baijiTv, wuxingTv, chongshaTv, jishenTv, xiongshenTv;
    TextView yiTv, jiTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainListView = findViewById(R.id.main_lv);
        imageButton = findViewById(R.id.main_img_button);
        imageButton.setOnClickListener(this);

        resultList = new ArrayList<>();
        adapter = new HistoryAdapter(this, resultList);
        mainListView.setAdapter(adapter);

        /** 加载某一天的数据 */

        // 获取日历对象
        calendar = Calendar.getInstance();
        date = new Date();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 添加头尾布局
        addHeaderAndFooterView();

        // 获取URL
        String todayHistory = ContentURL.getTodayHistoryURL("1.0", month, day);
        loadData(todayHistory);

        /** 因为ListView添加头布局了，所以position对应集合的位置发生变化，集合第0个数据，position为第1个数据，所以要减掉一个*/
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, HistoryDescActivity.class);
                HistoryBean.ResultBean resultBean = resultList.get(position - 1);
                String bean_id = resultBean.get_id();
                intent.putExtra("hisId", bean_id);
                startActivity(intent);
            }
        });
    }

    /**
     * 给ListView添加头尾布局函数
     */
    private void addHeaderAndFooterView() {
        // 将XML布局文件转换为布局对象
        View headerView = LayoutInflater.from(this).inflate(R.layout.main_headview, null);
        initHeaderView(headerView);
        mainListView.addHeaderView(headerView);
        View footerView = LayoutInflater.from(this).inflate(R.layout.main_footer, null);
        footerView.setTag("footer");
        footerView.setOnClickListener(this);
        mainListView.addFooterView(footerView);
    }

    /**
     * 初始化ListView头布局当中的每一个控件
     *
     * @param headerView
     */
    private void initHeaderView(View headerView) {
        // solarTv, dayTv, weekTv, lunarTv, baijiTv, wuxingTv, chongshaTv, jishenTv, xiongshenTv, yiTv, jiTv
        lunarTv = headerView.findViewById(R.id.main_tv_lunar);
        dayTv = headerView.findViewById(R.id.main_tv_day);
        weekTv = headerView.findViewById(R.id.main_tv_week);
        solarTv = headerView.findViewById(R.id.main_tv_solar);
        baijiTv = headerView.findViewById(R.id.main_header_tv_baiji);
        wuxingTv = headerView.findViewById(R.id.main_header_tv_wuxing);
        chongshaTv = headerView.findViewById(R.id.main_header_tv_chongsha);
        jishenTv = headerView.findViewById(R.id.main_header_tv_jishen);
        xiongshenTv = headerView.findViewById(R.id.main_header_tv_xiongshen);
        yiTv = headerView.findViewById(R.id.main_header_tv_yi);
        jiTv = headerView.findViewById(R.id.main_header_tv_ji);

        // 将日期对象转换成指定格式的字符串形式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        String laohuangliURL = ContentURL.getLaohuangliURL(time);
        loadHeaderData(laohuangliURL);
    }

    /**
     * 获取老黄历接口的数据
     *
     * @param laohuangliURL
     */
    private void loadHeaderData(String laohuangliURL) {
        RequestParams params = new RequestParams(laohuangliURL);
        x.http().get(params, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LunarBean huangliBean = new Gson().fromJson(result, LunarBean.class);
                LunarBean.ResultBean resultBean = huangliBean.getResult();
                lunarTv.setText("农历 " + resultBean.getYinli());
                String[] yangliArr = resultBean.getYangli().split("-");
                String week = getWeek(Integer.parseInt(yangliArr[0]), Integer.parseInt(yangliArr[1]), Integer.parseInt(yangliArr[2]));
                solarTv.setText("公历 " + yangliArr[0] + "年" + yangliArr[1] + "月" + yangliArr[2] + "日 " + week);

                dayTv.setText(yangliArr[2]);
                weekTv.setText(week);
                baijiTv.setText("【彭祖百忌】：" + resultBean.getBaiji());
                wuxingTv.setText("【五行】：" + resultBean.getWuxing());
                chongshaTv.setText("【冲煞】：" + resultBean.getChongsha());
                jishenTv.setText("【吉神宜趋】：" + resultBean.getJishen());
                xiongshenTv.setText("【凶神宜忌】：" + resultBean.getXiongshen());
                yiTv.setText("【宜】：" + resultBean.getYi());
                jiTv.setText("【忌】：" + resultBean.getJi());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 根据年月日获取对应的星期
     */
    private String getWeek(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        String weeks[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (index < 0) {
            index = 0;
        }
        return weeks[index];
    }

    /**
     * 获取数据成功过，在此方法中显示
     *
     * @param result
     */
    @Override
    public void onSuccess(String result) {
        // 先清空之前的数据
        resultList.clear();
        // 不能复制到局部变量中，否则 <<点击加载更多>> 会显示不出内容
        historyBean = new Gson().fromJson(result, HistoryBean.class);
        List<HistoryBean.ResultBean> list = historyBean.getResult();
        // 添加5条数据
        for (int i = 0; i < HISTORY_TODAY_DETAILS; i++) {
            resultList.add(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_img_button) {
            popCalendarDialog();
            return;
        }
        String tag = (String) v.getTag();
        if ("footer".equals(tag)) {
            // 点击更多的跳转，点击之后跳转到下一级页面
            Intent intent = new Intent(this, HistoryActivity.class);
            if (historyBean != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("history", historyBean);
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }
    }

    /**
     * 点击弹出日历对话框
     */
    private void popCalendarDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // 改变老黄历显示的内容
                String time = year + "-" + (month + 1) + "-" + dayOfMonth;
                String laohuangliURL = ContentURL.getLaohuangliURL(time);
                loadHeaderData(laohuangliURL);
                // 改变历史上的今天数据
                String todayHistoryURL = ContentURL.getTodayHistoryURL("1.0", (month + 1), dayOfMonth);
                loadData(todayHistoryURL);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}