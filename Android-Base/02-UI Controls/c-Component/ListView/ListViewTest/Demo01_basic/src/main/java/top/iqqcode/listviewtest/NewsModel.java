package top.iqqcode.listviewtest;

import java.util.Date;

/**
 * @Author: iqqcode
 * @Date: 2021-03-16 21:11
 * @Description:
 */
public class NewsModel {

    private String title;
    private String topic;
    private String newsTime;


    public NewsModel() {
    }

    public NewsModel(String title, String topic, String newsTime) {
        this.title = title;
        this.topic = topic;
        this.newsTime = newsTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }
}
