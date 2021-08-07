package top.iqqcode.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: jiazihui
 * @Date: 2021-08-05 22:45
 * @Description:获取网络数据
 */
public class GetNetData {
    /**
     * 定义一个获取网络图片数据的方法
     * @param path
     * @return
     * @throws Exception
     */
    public static byte[] getNetImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(3000); // 设置连接超时为5秒
        connection.setRequestMethod("GET"); // GET
        connection.setReadTimeout(3000); // 从主机读取数据的超时时间
        // 判断请求是否成功
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("请求url失败");
        }
        InputStream inStream = connection.getInputStream();
        byte[] bt = readData(inStream);
        inStream.close();
        return bt;
    }

    /**
     * 获取网页的html源代码
     * @param path
     * @return
     * @throws Exception
     */
    public static String getNetHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);
        if (connection.getResponseCode() == 200) {
            InputStream in = connection.getInputStream();
            byte[] data = readData(in);
            String html = new String(data, "UTF-8");
            return html;
        }
        return null;
    }

    /**
     * 从流中读取数据
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readData(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = inStream.read(buffer)) != -1)
        {
            outStream.write(buffer,0,len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
