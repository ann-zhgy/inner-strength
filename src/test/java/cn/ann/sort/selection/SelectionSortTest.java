package cn.ann.sort.selection;

import cn.ann.sort.Sort;
import cn.ann.sort.bubble.BubbleSort;
import cn.ann.util.Integers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Description：
 * <p>
 * Date: 2020-11-13 13:19
 *
 * @author 88475
 * @version v1.0
 */
class SelectionSortTest {
    @Test
    @DisplayName("test")
    void test() {
        Integer[] array = Integers.random(10000, 200, 300000);
        Sort<Integer> sort = new BubbleSort<>();
        sort.sort(array);
        Assertions.assertTrue(Integers.isAscOrder(array));
        System.out.println(sort);
    }
}