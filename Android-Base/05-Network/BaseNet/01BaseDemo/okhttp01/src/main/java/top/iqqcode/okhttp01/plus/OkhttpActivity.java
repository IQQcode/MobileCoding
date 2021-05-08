package top.iqqcode.okhttp01.plus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import top.iqqcode.okhttp01.R;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author: iqqcode
 * @Date: 2021/6/11
 * @Description:异步请求
 */
public class OkhttpActivity extends AppCompatActivity {

    private String url = "https://www.baidu.com/home/other/data/weatherInfo?city=%E6%AD%A6%E6%B1%89&indextype=manht&_req_seqid=0xfc7ab7ff0008878d&asyn=1&t=1588658358650&sid=1421_31125_21122_31426_31342_31270_31464_31228_30823_26350_31164";
    private EditText tvRes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        tvRes = findViewById(R.id.et_res);
        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequest();
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest();
            }
        });
    }

    /**
     * 异步get请求
     */
    private void getRequest() {
        // 第一步获取okHttpClient对象
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(8000, TimeUnit.MILLISECONDS)
                .build();
        // 第二步构建Request对象
        Request request = new Request.Builder()
                .url(url) // 可以后加请求参数 url + str
                .get()
                .build();
        // 第三步构建Call对象
        Call call = client.newCall(request);
        // 第四步:异步get请求
        call.enqueue(new Callback() {
            /**
             * 请求失败
             * @param call
             * @param e
             */
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("TAG", e.getMessage());
            }

            /**
             * 请求成功
             * @param call
             * @param response
             * @throws IOException
             */
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 得到的子线程
                String result = response.body().string();
                Log.e("TAG", result);
                tvRes.setText(result);
            }
        });
    }

    /**
     * 异步post请求
     */
    private void postRequest() {
        // 第一步创建OKHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(8000, TimeUnit.MILLISECONDS)
                .build();
        // 第二步创建RequestBody（Form表单）
        Map map = new HashMap();
        map.put("mobile", "demoData");
        map.put("password", "demoData");
        JSONObject jsonObject = new JSONObject(map);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);
        // 第三步创建Rquest
        Request request = new Request.Builder()
                .url("http://192.168.31.32:8080/renren-fast/app/login")
                .addHeader("contentType", "application/json;charset=UTF-8")
                .post(requestBodyJson)
                .build();
        // 第四步创建call回调对象
        final Call call = client.newCall(request);
        // 第五步发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                tvRes.setText(result);
            }
        });
    }
}