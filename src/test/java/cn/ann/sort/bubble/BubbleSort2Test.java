package cn.ann.sort.bubble;

import cn.ann.sort.Sort;
import cn.ann.util.Integers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Description：
 * <p>
 * Date: 2020-11-13 11:16
 *
 * @author 88475
 * @version v1.0
 */
class BubbleSort2Test {
    @Test
    @DisplayName("test")
    void test() {
        Integer[] array = Integers.random(10000, 200, 300000);
        Sort<Integer> sort = new BubbleSort2<>();
        sort.sort(array);
        Assertions.assertTrue(Integers.isAscOrder(array));
        System.out.println(sort);
    }

    @Test
    @DisplayName("test")
    void test2() {
        Integer[] array = Integers.ascOrder(200, 10200);
        Sort<Integer> sort = new BubbleSort2<>();
        sort.sort(array);
        Assertions.assertTrue(Integers.isAscOrder(array));
        System.out.println(sort);
    }
}