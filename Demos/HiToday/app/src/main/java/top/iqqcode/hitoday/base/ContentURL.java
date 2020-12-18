package top.iqqcode.hitoday.base;

/**
 * @Author: iqqcode
 * @Date: 2020-12-16 21:35
 * @Description:历史上的今天
 */
public class ContentURL {

    /**
     * 历史向上的今天：获取指定日期
     *
     * @param version
     * @param month
     * @param day
     * @return
     */
    public static String getTodayHistoryURL(String version, int month, int day) {
        String url = "http://api.juheapi.com/japi/toh?v=" + version + "&month=" + month + "&day=" + day + "&key=f9a10e3e91b07ac7b0433271bf021d8c";
        return url;
    }

    /**
     * 老黄历
     * @param time
     * @return
     */
    public static String getLaohuangliURL(String time) {
        String url = "http://v.juhe.cn/laohuangli/d?date=" + time + "&key=3c498180af8db645d0a6f50e518c8cbe";
        return url;
    }

    /**
     * 根据指定事件的ID获取指定事件的详情
     * @param version
     * @param id
     * @return
     * @Link http://api.juheapi.com/japi/tohdet?key=f9a10e3e91b07ac7b0433271bf021d8c&v=1.0&id=17701216
     */
    public static String getHistoryDescURL(String version, String id) {
        String url = "http://api.juheapi.com/japi/tohdet?key=f9a10e3e91b07ac7b0433271bf021d8c&v=" + version + "&id=" + id;
        return url;
    }
}