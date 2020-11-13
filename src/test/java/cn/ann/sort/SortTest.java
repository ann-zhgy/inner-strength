package cn.ann.sort;

import cn.ann.sort.bubble.BubbleSort;
import cn.ann.sort.bubble.BubbleSort2;
import cn.ann.sort.bubble.BubbleSort3;
import cn.ann.sort.selection.SelectionSort;
import cn.ann.util.Integers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description：各种排序算法比较
 * <p>
 * Date: 2020-11-13 13:22
 *
 * @author 88475
 * @version v1.0
 */
public class SortTest {
    @Test
    @DisplayName("排序比较")
    void test() {
        Integer[] array = Integers.random(10000, 200, 200000);

        sortCompare(array,
                new BubbleSort<>(),
                new BubbleSort2<>(),
                new BubbleSort3<>(),
                new SelectionSort<>());
    }

    @SafeVarargs
    private final <T extends Comparable<T>> void sortCompare(T[] array, Sort<T>... sorts) {
        for (Sort<T> sort : sorts) {
            sort.sort(Arrays.copyOf(array, array.length));
        }
        Arrays.sort(sorts);
        for (Sort<T> sort : sorts) {
            System.out.println(sort);
        }
    }

}
