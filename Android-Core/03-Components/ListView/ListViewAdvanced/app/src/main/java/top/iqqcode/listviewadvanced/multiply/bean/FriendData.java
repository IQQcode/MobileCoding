package top.iqqcode.listviewadvanced.multiply.bean;

import java.io.Serializable;

/**
 * @Author: jiazihui
 * @Date: 2022-12-04 19:42
 * @Description:
 */
public class FriendData implements Serializable {
    int messageIndex;
    String text;
    int next;

    public FriendData(int messageIndex, String text, int next) {
        this.messageIndex = messageIndex;
        this.text = text;
        this.next = next;
    }

    public int getMessageIndex() {
        return messageIndex;
    }

    public void setMessageIndex(int messageIndex) {
        this.messageIndex = messageIndex;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}