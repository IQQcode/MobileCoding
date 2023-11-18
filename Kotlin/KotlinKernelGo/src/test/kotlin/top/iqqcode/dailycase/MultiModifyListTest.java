package top.iqqcode.dailycase;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: iqqcode
 * @Date: 2023-11-03 00:26
 * @Description: TODO
 */
class MultiModifyListTest {

    @Test
    void fakeMultiModify() {
        MultiModifyList multiModify = new MultiModifyList(new ArrayList<>());
        multiModify.fakeMultiModify();
    }
}