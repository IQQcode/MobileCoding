package top.iqqcode.listviewadvanced.multiply.bean;

import java.io.Serializable;

/**
 * @Author: jiazihui
 * @Date: 2022-12-04 19:41
 * @Description:
 */
public class BotData implements Serializable {
    int messageIndex;
    String name;
    String content;
    String time;

    public BotData(int messageIndex, String name, String content, String time) {
        this.messageIndex = messageIndex;
        this.name = name;
        this.content = content;
        this.time = time;
    }

    public int getMessageIndex() {
        return messageIndex;
    }

    public void setMessageIndex(int messageIndex) {
        this.messageIndex = messageIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
