package top.iqqcode.collection;

/**
 * @Author: iqqcode
 * @Date: 2023-09-28 00:15
 * @Description:
 */
public interface PageMonitor {

    /**
     * 隐式为 public static final 修饰
     */
    boolean canWalk = false;

    /**
     * 默认实现
     */
    default void walk() {

    }

    int foo();
}
