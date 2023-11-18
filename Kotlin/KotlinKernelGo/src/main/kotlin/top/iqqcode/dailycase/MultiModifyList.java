package top.iqqcode.dailycase;

import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iqqcode
 * @Date: 2023-11-02 23:15
 * @Description: 模拟多线程场景下，多个线程同时向ArrayList add元素
 * 导致java.lang.ArrayIndexOutOfBoundsException: length=0; index=1的问题
 *
 * </p> <a href="https://chat.zhile.io/share/1f8cf2f6-826e-417f-b947-a9d178043324">gpt回答并发问题</a>
 */
public class MultiModifyList {

    // private final List<String> listeners = Collections.synchronizedList(new ArrayList<>());
    // private final ConcurrentLinkedQueue<String> listeners = new ConcurrentLinkedQueue<>();
    @Nullable
    private final List<String> listeners;

    public MultiModifyList(@Nullable List<String> list) {
        this.listeners = list;
    }

    public void fakeMultiModify() {
        int numThreads = 100;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    addElement("Element " + j);
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                if (thread != null) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("ArrayList size: " + listeners.size());

    }

    public void addElement(String value) {
        if (listeners != null) {
            listeners.add(value);
        }
    }


}
