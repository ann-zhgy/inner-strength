package cn.ann.sort.bubble;

import cn.ann.sort.Sort;

/**
 * Description：冒泡排序
 * <p>
 * Date: 2020-11-12 14:01
 *
 * @author 88475
 * @version v1.0
 */
public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (compare(j, j + 1) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }
}
