package top.iqqcode.demo.listen;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: iqqcode
 * @Date: 2021-08-14 20:38
 * @Description:
 */
public class MyAsyncTask extends AsyncTask<Void, Void, String> {

    private OnDataListener listener;
    private String mUrl;
    int responseCode = 0;

    public MyAsyncTask(String url) {
        this.mUrl = url;
    }

    public void setOnDataListener(OnDataListener asyncResponse) {
        this.listener = asyncResponse;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String responseData = "";
        try {
            URL url = new URL(mUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            InputStream in = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            responseData = sb.toString();
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接，防止内存泄漏
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return responseData;
    }

    @Override
    protected void onPostExecute(String msg) {
        super.onPostExecute(msg);
        if (msg != null | msg.length() != 0) {
            if (responseCode == 200) {
                listener.onDataSuccess(msg); // 任务完成时执行此方法
            } else {
                listener.onDataFailed(msg + " " + responseCode);
            }
        } else {
            msg = "网络无法请求";
        }
    }
}
