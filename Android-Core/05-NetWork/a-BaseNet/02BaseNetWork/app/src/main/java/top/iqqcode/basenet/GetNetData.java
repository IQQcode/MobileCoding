package top.iqqcode.basenet;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: iqqcode
 * @Date: 2021-08-07 23:00
 * @Description:获取网络数据
 */
public class GetNetData {
    /**
     * 获取网络图片数据的方法
     * @param path
     * @return
     * @throws Exception
     */
    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(3000); // 设置连接超时为3秒
        conn.setReadTimeout(3000); // 设置请求超时为3秒
        // 判断请求Url是否成功
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("请求url失败");
        }
        InputStream inStream = conn.getInputStream();
        byte[] bt = readStream(inStream);
        inStream.close();
        return bt;
    }

    /**
     * 获取网页的html源代码
     * @param path
     * @return
     * @throws Exception
     */
    public static String getHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream in = conn.getInputStream();
            byte[] data = readStream(in);
            String html = new String(data, "UTF-8");
            return html;
        }
        return null;
    }

    /**
     * 从流中读取数据
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] readStream(InputStream input) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = input.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        input.close();
        return outStream.toByteArray();
    }
}
