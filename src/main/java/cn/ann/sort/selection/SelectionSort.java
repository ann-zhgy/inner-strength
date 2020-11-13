package cn.ann.sort.selection;

import cn.ann.sort.Sort;

/**
 * Description：选择排序
 * <p>
 * Date: 2020-11-13 13:15
 *
 * @author 88475
 * @version v1.0
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int i = 0; i < array.length; i++) {
            int maxIndex = 0;
            int end = array.length - i;
            for (int j = 0; j < end; j++) {
                if (compare(maxIndex, j) > 0) {
                    maxIndex = j;
                }
            }
            swap(maxIndex, end - 1);
        }
    }
}
