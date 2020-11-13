package cn.ann.sort.bubble;

import cn.ann.sort.Sort;
import cn.ann.util.Integers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Description：
 * <p>
 * Date: 2020-11-13 11:25
 *
 * @author 88475
 * @version v1.0
 */
class BubbleSort3Test {
    @Test
    @DisplayName("乱序数组")
    void test() {
        Integer[] array = Integers.random(100000, 200, 300000);
        Sort<Integer> sort = new BubbleSort3<>();
        sort.sort(array);
        Assertions.assertTrue(Integers.isAscOrder(array));
        System.out.println(sort);
    }

    @Test
    @DisplayName("头升序")
    void test2() {
        Integer[] array = Integers.headAscOrder(200, 102000, 1000);
        Sort<Integer> sort = new BubbleSort3<>();
        sort.sort(array);
        Assertions.assertTrue(Integers.isAscOrder(array));
        System.out.println(sort);
    }

    @Test
    @DisplayName("尾升序")
    void test3() {
        Integer[] array = Integers.tailAscOrder(200, 102000, 1000);
        Sort<Integer> sort = new BubbleSort3<>();
        sort.sort(array);
        Assertions.assertTrue(Integers.isAscOrder(array));
        System.out.println(sort);
    }
}