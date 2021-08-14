package top.iqqcode.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.nio.channels.AsynchronousChannelGroup;

public class MainActivity extends AppCompatActivity {

    // 主布局中的UI组件
    private Button button, cancel; // 加载、取消按钮
    private TextView text; // 更新的UI组件
    private ProgressBar progressBar; // 进度条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // AsyncTask子类的实例必须在UI线程中创建
        MyAsyncTask mTask = new MyAsyncTask();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.execute();
            }
        });

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消一个正在执行的任务,onCancelled方法将会被调用
                mTask.cancel(true);
            }
        });
    }

    private void initView() {
        button = (Button) findViewById(R.id.button);
        cancel = (Button) findViewById(R.id.cancel);
        text = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

    }

    /**
     * 步骤1：创建AsyncTask子类
     * 注：
     * a. 继承AsyncTask类
     * b. 为3个泛型参数指定类型；若不使用，可用java.lang.Void类型代替
     * 此处指定为：输入参数 = String类型、执行进度 = Integer类型、执行结果 = String类型
     * c. 根据需求，在AsyncTask子类内实现核心方法
     */

    private class MyAsyncTask extends AsyncTask<String, Integer, String> {

        // 作用：执行 线程任务前的操作
        @Override
        protected void onPreExecute() {
            text.setText("加载中");
            // 执行前显示提示
        }

        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        // 此处通过计算从而模拟“加载进度”的情况
        @Override
        protected String doInBackground(String... strings) {
            try {
                int count = 0;
                int length = 1;
                while (count < 99) {

                    count += length;
                    // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
                    publishProgress(count);
                    // 模拟耗时任务
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        // 作用：在主线程 显示线程任务执行的进度
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            text.setText("loading..." + values[0] + "%");
        }

        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        @Override
        protected void onPostExecute(String result) {
            // 执行完毕后，则更新UI
            text.setText("加载完毕");
        }

        // 作用：将异步任务设置为：取消状态
        @Override
        protected void onCancelled() {
            text.setText("已取消");
            progressBar.setProgress(0);

        }
    }
}